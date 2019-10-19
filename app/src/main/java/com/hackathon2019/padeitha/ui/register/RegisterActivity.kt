package com.hackathon2019.padeitha.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.BaseActivity
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

    }

    override fun onStart() {
        super.onStart()

        btnMale.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        btnFemale.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))

    }

}
