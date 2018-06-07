package com.frostfel.starterproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AlertDialog

open class BaseActivity : AppCompatActivity() {

    /**Add here common methods for all activities **/

    fun showDialog(title: String, message: String, mainText: String, cancelText: String = "Cancel", mainListener: () -> Unit, cancelListener: () -> Unit = {}, showSecondary : Boolean = true) {
        val dialog = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(mainText, { _, _ -> mainListener() })

        if(showSecondary) dialog.setNegativeButton(cancelText, { _, _ -> cancelListener()})
        dialog.create()
                .apply { setCanceledOnTouchOutside(false) }

                .show()
    }
}
