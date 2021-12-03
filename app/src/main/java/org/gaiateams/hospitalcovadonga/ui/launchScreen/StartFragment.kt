package org.gaiateams.hospitalcovadonga.ui.launchScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.gaiateams.hospitalcovadonga.R
import org.gaiateams.hospitalcovadonga.databinding.FragmentStartBinding
import android.content.Intent
import android.net.Uri
import org.gaiateams.hospitalcovadonga.utils.ACTUAL_DATE
import org.gaiateams.hospitalcovadonga.utils.ACTUAL_HOUR


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.txtvwDate.text = ACTUAL_DATE
        binding.txtvwHour.text = ACTUAL_HOUR

        binding.bttnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment, null)
        }
        binding.bttnAnon.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_citationNoUserFragment, null)
        }
        binding.fabEmergencyCall.setOnClickListener {
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse("tel:2713154800")
            startActivity(i)
        }

        return binding.root
    }
}