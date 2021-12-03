package org.gaiateams.hospitalcovadonga.ui.launchScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import org.gaiateams.hospitalcovadonga.AuthenticationActivity
import org.gaiateams.hospitalcovadonga.R
import org.gaiateams.hospitalcovadonga.databinding.FragmentRegisterBinding
import org.gaiateams.hospitalcovadonga.utils.SAVED_USER
import org.gaiateams.hospitalcovadonga.utils.auth
import org.gaiateams.hospitalcovadonga.utils.preferences

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.bttnRegister.setOnClickListener {
            if (binding.txtMail.text.toString().isNotEmpty() && binding.txtPasswordOne.text.toString().isNotEmpty()
                && binding.txtPasswordOne.text.toString() == binding.txtPasswordTwo.text.toString()
            ) {
                val preferencesEditor = preferences.edit()

                auth.createUserWithEmailAndPassword(binding.txtMail.text.toString(), binding.txtPasswordOne.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        preferencesEditor.putString(SAVED_USER, it.result!!.user!!.email)
                        preferencesEditor.apply()

                        (activity as AuthenticationActivity).onBackPressed()
                    } else {
                        MaterialAlertDialogBuilder(requireActivity())
                            .setTitle(R.string.signin_error)
                            .setMessage(R.string.signin_error_description)
                            .setPositiveButton(R.string.confirm) { dialog, which ->
                            }
                            .show()
                    }
                }
            } else if (binding.txtCurp.text.toString().isEmpty()) {
                binding.txtlytCurp.isErrorEnabled = true
                binding.txtlytCurp.error = resources.getString(R.string.empty_curp_description)
                binding.txtlytCurp.errorIconDrawable = null
            } else if (binding.txtMail.text.toString().isEmpty()) {
                binding.txtlytMail.isErrorEnabled = true
                binding.txtlytMail.error = resources.getString(R.string.empty_email_description)
                binding.txtlytMail.errorIconDrawable = null
            } else if (binding.txtPasswordOne.text.toString().isEmpty()) {
                binding.txtlytPasswordOne.isErrorEnabled = true
                binding.txtlytPasswordOne.error = resources.getString(R.string.empty_password_description)
                binding.txtlytPasswordOne.errorIconDrawable = null
            } else if (binding.txtPasswordOne.text.toString() != binding.txtPasswordTwo.text.toString()) {
                binding.txtlytPasswordTwo.error = resources.getString(R.string.unequal_password_description)
                binding.txtlytPasswordTwo.errorIconDrawable = null
            }
        }

        binding.chkAcceptTerms.setOnClickListener {
            binding.bttnRegister.isEnabled = binding.chkAcceptTerms.isChecked
        }

        binding.txtPasswordOne.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                binding.txtlytPasswordOne.isErrorEnabled = false
            }
        })

        binding.txtMail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                binding.txtlytMail.isErrorEnabled = false
            }
        })

        binding.txtCurp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                binding.txtlytCurp.isErrorEnabled = false
            }
        })

        binding.txtPasswordTwo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (binding.txtPasswordOne.text.toString() != binding.txtPasswordTwo.text.toString()) {
                    binding.txtlytPasswordTwo.error = resources.getString(R.string.unequal_password_description)
                    binding.txtlytPasswordTwo.errorIconDrawable = null
                } else {
                    binding.txtlytPasswordTwo.error = null
                }
            }
        })

        return binding.root
    }
}