package com.hackathon2019.padeitha.ui.base

import android.graphics.PorterDuff
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hackathon2019.padeitha.R
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    val compositeDisposable: CompositeDisposable by inject()

    fun toast(message: String) {
        try {
            val toast = Toast.makeText(
                requireContext(),
                message,
                Toast.LENGTH_SHORT
            )
            val view = toast.view

            view.background.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.colorAccent),
                PorterDuff.Mode.SRC_IN
            )

            val textView = view.findViewById<TextView>(android.R.id.message)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            textView.gravity = Gravity.CENTER

            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    fun setDialog(show: Boolean) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(R.layout.progress)
        val dialog = builder.create()
        if (show)
            dialog.show()
        else
            dialog.dismiss()
    }


}