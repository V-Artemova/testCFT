package ru.kuz.cft
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.kuz.cft.databinding.ActivitySecondScreenBinding


class SecondScreen : AppCompatActivity() {
   private  lateinit var binding: ActivitySecondScreenBinding
    var  need:String? = null
    companion object { const val TOTAL_FIRSTNAME = "total_firsName" }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second_screen)


        need = intent.getStringExtra(TOTAL_FIRSTNAME)

        binding.btnGreeting.setOnClickListener {
            val dialog = Dialog()
           dialog.show(supportFragmentManager, "") } }


    fun getMyData(): String? { return need }
    override fun onBackPressed() {
finishAffinity()
        // do nothing
    }

}


