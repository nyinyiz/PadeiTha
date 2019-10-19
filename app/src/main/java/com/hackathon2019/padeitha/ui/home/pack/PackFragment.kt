package com.hackathon2019.padeitha.ui.home.pack

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import coil.api.load
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.domain.responses.packs.Pack
import com.hackathon2019.padeitha.ui.base.BaseFragment
import com.hackathon2019.padeitha.ui.home.HomeViewModel
import com.hackathon2019.padeitha.ui.services.PackDetailActivity
import com.list.rados.fast_list.bind
import com.list.rados.fast_list.update
import kotlinx.android.synthetic.main.fragment_pack.*
import kotlinx.android.synthetic.main.item_view_pack.view.*
import okhttp3.HttpUrl
import org.koin.android.viewmodel.ext.android.viewModel

class PackFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance(): PackFragment {
            return PackFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pack,container,false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadPacks()
        bindView()
    }

    private fun bindView() {

        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = false
        }

        rvPacks.bind(ArrayList<Pack>(),R.layout.item_view_pack) { data ->

            ivPack.load(url = HttpUrl.parse(data.PackImgURL)) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)

            }
            tvTitle.text = data.PackTitle
            tvPartner.text = data.PackPartner

            this.setOnClickListener {
                requireContext().startActivity(PackDetailActivity.newInstance(requireContext(),data))
            }

        }.layoutManager(GridLayoutManager(requireContext(),2))


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewStatePacks.observe(this, Observer {
            when (it) {
                is PacksViewState.Loading -> {
                    swipeLayout.isRefreshing = true
                }
                is PacksViewState.InternetConnection -> {
                    toast(it.msg)
                }
                is PacksViewState.DataReady -> {
                    swipeLayout.isRefreshing = false
                    rvPacks.update(it.response.packs)
                }
                is PacksViewState.Error -> {
                    swipeLayout.isRefreshing = false
                    toast(it.error.localizedMessage)
                }
            }
        })
    }

}