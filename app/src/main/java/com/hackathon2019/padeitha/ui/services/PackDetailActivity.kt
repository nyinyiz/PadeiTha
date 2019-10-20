package com.hackathon2019.padeitha.ui.services

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.domain.responses.packs.Pack
import com.hackathon2019.padeitha.ui.base.BaseActivity
import com.hackathon2019.padeitha.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_pack_detail.*
import kotlinx.android.synthetic.main.title_back_bar_view.*
import okhttp3.HttpUrl

class PackDetailActivity : BaseActivity() {

    override fun getLayoutView(): Int {
        return R.layout.activity_pack_detail
    }

    companion object {
        private lateinit var packData : Pack
        fun newInstance(context: Context, data : Pack): Intent {
            packData = data
            return Intent(context, PackDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()

        ivBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun bindData() {
        tvTitle.text = getString(R.string.pack_desc)
        ivPack.load(url = HttpUrl.parse(packData.PackImgURL)) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
            error(R.drawable.placeholder)
        }
        tvPackTitle.text = packData.PackTitle
        tvPackPartner.text = packData.PackPartner
        tvPackPostedDate.text = packData.CreatedAt
        tvContactPhone.text = packData.ContactNo
        tvDetail.text = packData.PackDescription

        btnApply.setOnClickListener {
            startActivity(RegisterActivity.newInstance(this))
        }
    }
}
