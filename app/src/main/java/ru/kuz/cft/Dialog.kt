package ru.kuz.cft

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Dialog:DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val myDataFromActivity: String? = ( activity as SecondScreen).getMyData()
        val builder = AlertDialog.Builder(context)
         .setTitle("Greeting")
         .setMessage("Hello, $myDataFromActivity")
         .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
         }).create()
        return builder }



}
