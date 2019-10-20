package com.hackathon2019.padeitha.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.BaseActivity
import com.hackathon2019.padeitha.ui.home.help.HelpFragment
import com.hackathon2019.padeitha.ui.home.news.NewsFragment
import com.hackathon2019.padeitha.ui.home.pack.PackFragment
import com.hackathon2019.padeitha.ui.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_bar_view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun getLayoutView(): Int {
        return R.layout.activity_main

    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadNews()
        viewModel.loadPacks()

        ivNoti.setOnClickListener {
            startActivity(NotificationActivity.newInstance(this))
        }

        val packFragment = PackFragment.newInstance()
        val newsFragment = NewsFragment.newInstance()
        val helpFragment = HelpFragment.newInstance()

        var fm = supportFragmentManager
        var active: Fragment = packFragment

        fm.beginTransaction().add(R.id.frameLayout, packFragment, "Pack").hide(packFragment)
            .commit()
        fm.beginTransaction().add(R.id.frameLayout, newsFragment, "News").hide(newsFragment)
            .commit()
        fm.beginTransaction().add(R.id.frameLayout, helpFragment, "Help").hide(helpFragment)
            .commit()

        fm.beginTransaction().show(packFragment).commit()
        tvTitle.text = getString(R.string.title_1)

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_pack -> {
                    fm.beginTransaction().hide(active).show(packFragment).commit()
                    active = packFragment
                    tvTitle.text = getString(R.string.title_1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_news -> {

                    fm.beginTransaction().hide(active).show(newsFragment).commit()
                    active = newsFragment
                    tvTitle.text = getString(R.string.title_2)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_help -> {

                    fm.beginTransaction().hide(active).show(helpFragment).commit()
                    active = helpFragment
                    tvTitle.text = getString(R.string.title_3)
                    return@setOnNavigationItemSelectedListener true

                }

            }

            return@setOnNavigationItemSelectedListener false

        }


    }


}
