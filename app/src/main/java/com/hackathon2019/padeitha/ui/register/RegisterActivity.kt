package com.hackathon2019.padeitha.ui.register

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.BaseActivity
import com.hackathon2019.padeitha.ui.base.DialogClickCallBack
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.title_back_bar_view.*

class RegisterActivity : BaseActivity() {
    override fun getLayoutView(): Int {
        return R.layout.activity_register
    }


    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvTitle.text = getString(R.string.user_form)
        scrollView.visibility = View.VISIBLE
        supportView.visibility = View.GONE

        btnMale.setOnClickListener {

            btnMale.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            btnFemale.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))

        }

        btnFemale.setOnClickListener {

            btnFemale.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            btnMale.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener {

            scrollView.visibility = View.GONE
            supportView.visibility = View.VISIBLE
        }

        btnFinish.setOnClickListener {

            showError("",getString(R.string.lone_alert),object : DialogClickCallBack {
                override fun onClickPositive(dialog: DialogInterface) {
                    dialog.dismiss()
                    finish()
                }

                override fun onClickNegative(dialog: DialogInterface) {
                    dialog.dismiss()
                }

            })
        }
    }

    override fun onStart() {
        super.onStart()

        btnMale.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        btnFemale.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))

    }

}
