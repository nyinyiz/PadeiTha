package com.hackathon2019.padeitha.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import coil.api.load
import coil.transform.CircleCropTransformation
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.domain.responses.news.New
import com.hackathon2019.padeitha.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.title_back_bar_view.*
import okhttp3.HttpUrl

class NewsDetailActivity : BaseActivity() {

    override fun getLayoutView(): Int {
        return R.layout.activity_news_detail
    }

    companion object {
        private lateinit var newsData : New
        fun newInstance(context: Context, data : New): Intent {
            newsData = data
            return Intent(context, NewsDetailActivity::class.java)
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

        tvTitle.text = getString(R.string.news_detail)
        if (newsData.PostImage != "") {
            ivPost.visibility = View.VISIBLE
            ivPost.load(url = HttpUrl.parse(newsData.PostImage)) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)

            }
        }else {
            ivPost.visibility = View.GONE
        }

        ivPostedUserImage.load(url = HttpUrl.parse(newsData.PostedUserImg)) {
            crossfade(true)
            placeholder(R.drawable.ic_profile_grey)
            transformations(CircleCropTransformation())
        }

        tvPostTitle.text = newsData.Title
        tvPostedUser.text = newsData.PostedUser
        tvPostedDate.text = newsData.PostedDate
        tvDescription.text = newsData.Body


    }
}
