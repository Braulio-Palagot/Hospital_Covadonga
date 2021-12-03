package org.gaiateams.hospitalcovadonga.ui.mainScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.gaiateams.hospitalcovadonga.R
import org.gaiateams.hospitalcovadonga.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.imgUser.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment, null)
        }

        binding.bttnCreateCitation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_citationWithUserFragment, null)
        }
        binding.fabEmergencyCall.setOnClickListener {
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse("tel:2713154800")
            startActivity(i)
        }

        return binding.root
    }
}