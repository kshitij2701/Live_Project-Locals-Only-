package com.example.liveproject.ui.view.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.liveproject.R
import com.example.liveproject.databinding.FragmentPopUpDeleteAccountBinding


class PopUpDeleteAccount : DialogFragment() {

    private lateinit var binding: FragmentPopUpDeleteAccountBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Ensures background is transparent
        dialog.window?.setDimAmount(0.8f)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPopUpDeleteAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteAccountLayoutCancelBtn.setOnClickListener {
            dismiss()
        }
    }

}

