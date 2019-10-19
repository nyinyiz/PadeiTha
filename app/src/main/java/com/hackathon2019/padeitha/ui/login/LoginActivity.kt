package com.hackathon2019.padeitha.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.BaseActivity
import com.hackathon2019.padeitha.ui.home.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun getLayoutView(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        btnFbLogin.setOnClickListener {
            startActivity(MainActivity.newInstance(this))
        }

    }
}
