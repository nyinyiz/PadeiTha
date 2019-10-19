package com.hackathon2019.padeitha.ui.home.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.BaseFragment
import com.hackathon2019.padeitha.ui.dialog.EmergencyCallPhoneDialogFragment
import com.hackathon2019.padeitha.ui.home.HomeViewModel
import com.hackathon2019.padeitha.ui.questionAnswer.QuestionAnswerActivity
import com.hackathon2019.padeitha.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.fragment_help.*
import org.koin.android.viewmodel.ext.android.viewModel

class HelpFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance(): HelpFragment{
            return HelpFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help,container,false)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnEmergency.setOnClickListener {
            requireContext().startActivity(RegisterActivity.newInstance(requireContext()))
        }

        btnEmergencyCall.setOnClickListener {

            val fragment = EmergencyCallPhoneDialogFragment.newInstance()
            fragment.show(requireActivity().supportFragmentManager,"PhoneCall")

        }

        btnQuestionAnswer.setOnClickListener {
            requireContext().startActivity(QuestionAnswerActivity.newInstance(requireContext()))
        }
    }

}