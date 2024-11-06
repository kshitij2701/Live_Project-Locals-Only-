package com.example.liveproject.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.liveproject.R
import com.example.liveproject.data.local.PreferencesManager
import com.example.liveproject.databinding.FragmentHomeBinding
import com.example.liveproject.databinding.FragmentProfileBinding
import com.example.liveproject.ui.view.activities.SignUpActivity


class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize PreferencesManager
        preferencesManager = PreferencesManager(requireContext())

        // Set up navigation
        setupNavigation()


    }

    private fun setupNavigation() {
        binding.apply {
            personalProfileLayout.setOnClickListener {
                findNavController().navigate(R.id.action_profile2_to_editProfile)
            }
            shippingAddressLayout.setOnClickListener {
//                findNavController().navigate(R.id.action_account_fragment_to_contactFragment)
            }
            paymentMethodsLayout.setOnClickListener {
                findNavController().navigate(R.id.action_profile2_to_paymentMethods)
            }
            languageLayout.setOnClickListener {
//                findNavController().navigate(R.id.action_account_fragment_to_privacyPolicyFragment)
            }
            aboutLayout.setOnClickListener {
                findNavController().navigate(R.id.action_profile2_to_about)
            }
            termsConditionLayout.setOnClickListener {
                findNavController().navigate(R.id.action_profile2_to_termsCondition)
            }

            logoutBtn.setOnClickListener {
                // Clear user data
                preferencesManager.clearUserEmail()

                // Show a toast message
                Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT)
                    .show()

                // Navigate back to login/signup screen
                val intent = Intent(requireActivity(), SignUpActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish() // Close the activity to prevent back navigation
            }

            deleteAccountBtn.setOnClickListener {
                val showPopUp = PopUpDeleteAccount()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
            }

        }
    }

}