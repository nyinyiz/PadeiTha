package com.hackathon2019.padeitha.ui.dialog

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.hackathon2019.padeitha.R
import com.hackathon2019.padeitha.ui.base.DialogClickCallBack
import com.hackathon2019.padeitha.utils.PERMISSIONS_REQUEST_CALL_PHONE
import kotlinx.android.synthetic.main.dialog_fragment_phone_call.*

class EmergencyCallPhoneDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(): EmergencyCallPhoneDialogFragment {
            return EmergencyCallPhoneDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_phone_call, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ivClose.setOnClickListener {
            dismiss()
        }


        btnPhoneOne.setOnClickListener {
            callPhone(btnPhoneOne.text.toString())
        }

        btnPhoneTwo.setOnClickListener {
            callPhone(btnPhoneTwo.text.toString())
        }

        btnPhoneThree.setOnClickListener {
            callPhone(btnPhoneThree.text.toString())
        }

        btnPhoneFour.setOnClickListener {
            callPhone(btnPhoneFour.text.toString())
        }

    }

    private fun callPhone(phoneNo: String) {

        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo))

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.CALL_PHONE
                )
            ) {
                showError("Alert", "You need to allow permission to continue process", object :
                    DialogClickCallBack {
                    override fun onClickPositive(dialog: DialogInterface) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.CALL_PHONE),
                            PERMISSIONS_REQUEST_CALL_PHONE
                        )
                        dialog.dismiss()

                    }

                    override fun onClickNegative(dialog: DialogInterface) {
                        dialog.dismiss()
                    }

                })
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    PERMISSIONS_REQUEST_CALL_PHONE
                )
            }
        } else {
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CALL_PHONE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    toast("Permission granted")
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    toast("Permission denied")
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
                toast("Permission ignored")
            }
        }
    }

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

    fun showError(title: String, errorMessage: String?, callBack: DialogClickCallBack) {
        if (errorMessage != null) {
            AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(errorMessage)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog, which ->
                    callBack.onClickPositive(dialog)
                }
                .setNegativeButton(android.R.string.no) { dialog, which ->
                    callBack.onClickNegative(dialog)
                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()

        }
    }

}