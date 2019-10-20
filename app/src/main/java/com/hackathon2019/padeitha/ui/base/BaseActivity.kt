package com.hackathon2019.padeitha.ui.base

import android.content.DialogInterface
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hackathon2019.padeitha.R


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutView())

    }

    abstract fun getLayoutView(): Int

    fun toast(message: String) {
        try {
            val toast = Toast.makeText(
                applicationContext,
                message,
                Toast.LENGTH_SHORT
            )
            val view = toast.view

            view.background.setColorFilter(
                ContextCompat.getColor(applicationContext, R.color.colorAccent),
                PorterDuff.Mode.SRC_IN
            )

            val textView = view.findViewById<TextView>(android.R.id.message)
            textView.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            textView.gravity = Gravity.CENTER

            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun showError(title: String, errorMessage: String?, callBack: DialogClickCallBack) {
        if (errorMessage != null) {
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(errorMessage)
                .setPositiveButton(
                    R.string.yes
                ) { dialog, which ->
                    callBack.onClickPositive(dialog)
                }
                .setNegativeButton(R.string.cancel) { dialog, which ->
                    callBack.onClickNegative(dialog)
                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()

        }
    }

}

interface DialogClickCallBack {
    fun onClickPositive(dialog: DialogInterface)
    fun onClickNegative(dialog: DialogInterface)
}