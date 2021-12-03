package org.gaiateams.hospitalcovadonga.ui.launchScreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.gaiateams.hospitalcovadonga.AuthenticationActivity
import org.gaiateams.hospitalcovadonga.MainActivity
import org.gaiateams.hospitalcovadonga.R
import org.gaiateams.hospitalcovadonga.databinding.FragmentLoginBinding
import org.gaiateams.hospitalcovadonga.utils.IS_LOGGED
import org.gaiateams.hospitalcovadonga.utils.SAVED_USER
import org.gaiateams.hospitalcovadonga.utils.auth
import org.gaiateams.hospitalcovadonga.utils.preferences

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.bttnLogin.setOnClickListener {
            try {
                auth.signInWithEmailAndPassword(
                    binding.txtMail.text.toString(),
                    binding.txtPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val preferencesEditor = preferences.edit()
                        preferencesEditor.putString(
                            SAVED_USER,
                            it.result?.user?.email
                        )
                        val login = Intent(activity, MainActivity::class.java)
                        if (binding.chkKeepLogin.isChecked) {
                            preferencesEditor.putBoolean(
                                IS_LOGGED,
                                true
                            )
                        }
                        preferencesEditor.apply()

                        startActivity(login)

                        (activity as AuthenticationActivity).finish()
                    } else {
                        MaterialAlertDialogBuilder(requireActivity())
                            .setTitle(R.string.no_user_found)
                            .setMessage(R.string.no_user_found_description)
                            .setPositiveButton(R.string.confirm) { dialog, which ->
                            }
                            .show()
                    }
                }
            } catch (e: Exception) {
//                FirebaseCrashlytics.getInstance().log("Error durante login.")
//                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }

        binding.bttnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment, null)
        }

        return binding.root
    }
}