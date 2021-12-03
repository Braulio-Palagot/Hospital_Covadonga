package org.gaiateams.hospitalcovadonga.ui.mainScreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gaiateams.hospitalcovadonga.AuthenticationActivity
import org.gaiateams.hospitalcovadonga.MainActivity
import org.gaiateams.hospitalcovadonga.databinding.FragmentProfileBinding
import org.gaiateams.hospitalcovadonga.utils.IS_LOGGED
import org.gaiateams.hospitalcovadonga.utils.SAVED_USER
import org.gaiateams.hospitalcovadonga.utils.preferences

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.bttnLogout.setOnClickListener {
            val preferencesEditor = preferences.edit()
            preferencesEditor.putBoolean(
                IS_LOGGED,
                false
            )
            preferencesEditor.apply()

            val logout = Intent(activity, AuthenticationActivity::class.java)
            startActivity(logout)
            (activity as MainActivity).finish()
        }

        return binding.root
    }
}