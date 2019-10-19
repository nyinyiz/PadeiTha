package com.hackathon2019.padeitha.ui.questionAnswer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.domain.responses.faq.Faq
import com.hackathon2019.padeitha.ui.base.BaseActivity
import com.hackathon2019.padeitha.ui.home.HomeViewModel
import com.list.rados.fast_list.bind
import com.list.rados.fast_list.update
import kotlinx.android.synthetic.main.activity_question_answer.*
import kotlinx.android.synthetic.main.item_view_question_answer.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionAnswerActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun getLayoutView(): Int {
        return R.layout.activity_question_answer
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, QuestionAnswerActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        viewModel.loadFAQ()

        ivBack.setOnClickListener {
            onBackPressed()
        }

        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = false
        }

        viewModel.viewStateFAQ.observe(this, Observer {
            when (it) {
                is FAQViewState.Loading -> {
                    swipeLayout.isRefreshing = true
                }
                is FAQViewState.InternetConnection -> {
                    toast(it.msg)
                }
                is FAQViewState.DataReady -> {
                    swipeLayout.isRefreshing = false
                    rvQuestions.update(it.response.faqs)
                }
                is FAQViewState.Error -> {
                    swipeLayout.isRefreshing = false
                    Log.d("ERROR ","ERROR : ${it.error.localizedMessage}")
                    toast(it.error.localizedMessage)
                }
            }
        })

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadFAQ()

    }

    private fun bindView() {

        rvQuestions.bind(ArrayList<Faq>(),R.layout.item_view_question_answer) { data ->

            tvQuestion.text = data.question
            tvAnswer.text = data.answer

            this.setOnClickListener {
                if (tvAnswer.isVisible) {
                    tvAnswer.visibility = View.GONE
                }else {
                    tvAnswer.visibility = View.VISIBLE
                }
            }
        }.layoutManager(LinearLayoutManager(this))


    }
}
