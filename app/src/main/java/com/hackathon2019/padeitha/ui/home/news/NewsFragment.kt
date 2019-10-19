package com.hackathon2019.padeitha.ui.home.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import coil.transform.CircleCropTransformation
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.domain.responses.news.New
import com.hackathon2019.padeitha.ui.base.BaseFragment
import com.hackathon2019.padeitha.ui.home.HomeViewModel
import com.hackathon2019.padeitha.ui.news.NewsDetailActivity
import com.list.rados.fast_list.bind
import com.list.rados.fast_list.update
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_view_news.view.*
import okhttp3.HttpUrl
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadNews()
        bindView()
    }


    private fun bindView() {

        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = false
        }

        rvNews.bind(ArrayList<New>(),R.layout.item_view_news) { data ->

            if (data.PostImage != "") {
                ivNews.visibility = View.VISIBLE
                ivNews.load(url = HttpUrl.parse(data.PostImage)) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)

                }
            }else {
                ivNews.visibility = View.GONE
            }

            ivIcon.load(url = HttpUrl.parse(data.PostedUserImg)) {
                crossfade(true)
                placeholder(R.drawable.ic_profile_grey)
                transformations(CircleCropTransformation())
            }

            tvTitle.text = data.PostedUser
            tvPostedDate.text = data.PostedDate
            tvDes.text = data.Title

            this.setOnClickListener {
                requireContext().startActivity(NewsDetailActivity.newInstance(requireContext(),data))
            }

        }.layoutManager(LinearLayoutManager(requireContext()))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewStateNews.observe(this, Observer {
            when (it) {
                is NewsViewState.Loading -> {
                    swipeLayout.isRefreshing = true
                }
                is NewsViewState.InternetConnection -> {
                    toast(it.msg)
                }
                is NewsViewState.DataReady -> {
                    swipeLayout.isRefreshing = false
                    rvNews.update(it.response.news)
                }
                is NewsViewState.Error -> {
                    swipeLayout.isRefreshing = false
                    Log.d("ERROR ","ERROR : ${it.error.localizedMessage}")
                    toast(it.error.localizedMessage)
                }
            }
        })

    }


}