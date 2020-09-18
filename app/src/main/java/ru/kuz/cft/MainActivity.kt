package ru.kuz.cft
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.kuz.cft.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    var Key: Array<Boolean> = arrayOf(false, false, false, false, false)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        datePickShow()
        checkEmptiness()

        binding.buttonReg.setOnClickListener {
                       if (validation()) {
                openSecondScreen() }
                       else Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_SHORT)
                .show()
            validation()

        }
    }

    private fun getValueKey(id: Int, key: Boolean) {
        Key[id] = key
        ButActive(Key)
    }

    private fun ButActive(keyMass: Array<Boolean>) {
        binding.buttonReg.isEnabled =
            (keyMass[0] && keyMass[1] && keyMass[2] && keyMass[3] && keyMass[4])
    }


    private fun checkEmptiness() {
        binding.firstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var keyfirstName = (s.toString().isNotEmpty())
                getValueKey(0, keyfirstName); }
        })

        binding.lastName.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var keylastName = (s.toString().isNotEmpty())
                    getValueKey(1, keylastName)
                }
            })

        binding.passWord.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var keyPassWord = (s.toString().isNotEmpty())
                    getValueKey(2, keyPassWord)
                }
            })

        binding.passCheck.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var keyCheckPass = (s.toString().isNotEmpty())
                    getValueKey(3, keyCheckPass)
                }
            })

        binding.birthDay.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var keyBirthday = (s.toString().isNotEmpty())
                    getValueKey(4, keyBirthday)
                }
            })
    }


    private fun validation(): Boolean {
        val Name = Regex("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}" + "$")
        val keyfirstName = Name.containsMatchIn(binding.firstName.text.toString())
        val keylastName = Name.containsMatchIn(binding.lastName.text.toString())
        if (!keyfirstName) {
            binding.firstName.error = "Limit of 2-20 characters, which can be lattin letters"
            binding.buttonReg.isEnabled = false

            return false
        }
        if (!keylastName) {
            binding.lastName.error = "Limit of 2-20 characters, which can be latin letters"
            binding.buttonReg.isEnabled = false
            return false
        }

        val Pass = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)." + "*$")
        val keyPass = Pass.containsMatchIn(binding.passWord.text.toString())

        if (!keyPass) {
            binding.passWord.error = "Lowercase and uppercase letters and numbers"
            binding.buttonReg.isEnabled = false
            return false
        }
        if (binding.passWord.text.toString() != binding.passCheck.text.toString()) {
            binding.passCheck.error = "Passwords don't match"
            binding.buttonReg.isEnabled = false
            return false
        }
        return true }



private fun datePickShow(){
    val dataPick = Calendar.getInstance()
    binding.birthDay.setOnClickListener {
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view: DatePicker, mYear:Int, mMonth:Int, mDay:Int ->


            binding.birthDay.setText(""+mDay+ "." + mMonth + "."+mYear ) },dataPick.get(Calendar.YEAR),dataPick.get(Calendar.MONTH),dataPick.get(Calendar.DAY_OF_MONTH))
        dpd.show()

    } }




    private fun openSecondScreen() {

            val openScreen = Intent(this, SecondScreen::class.java)
            openScreen.putExtra(SecondScreen.TOTAL_FIRSTNAME, binding.firstName.text.toString())
            startActivity(openScreen)

    }
}













