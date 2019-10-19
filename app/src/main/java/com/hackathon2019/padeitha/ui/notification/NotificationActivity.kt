package com.hackathon2019.padeitha.ui.notification

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.BaseActivity
import com.hackathon2019.padeitha.utils.getSampleList
import com.list.rados.fast_list.bind
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.item_view_notification.view.*
import kotlinx.android.synthetic.main.title_back_bar_view.*

class NotificationActivity : BaseActivity() {
    override fun getLayoutView(): Int {
        return R.layout.activity_notification

    }


    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, NotificationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivBack.setOnClickListener {
            onBackPressed()
        }

        rvNotification.bind(getSampleList(),R.layout.item_view_notification) {data ->
            if (data == "b") {
                tvStatus.visibility = View.GONE
            }

        }.layoutManager(LinearLayoutManager(this))
    }
}
