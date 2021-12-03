package org.gaiateams.hospitalcovadonga.ui.launchScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.gaiateams.hospitalcovadonga.AuthenticationActivity
import org.gaiateams.hospitalcovadonga.MainActivity
import org.gaiateams.hospitalcovadonga.R
import org.gaiateams.hospitalcovadonga.databinding.FragmentCitationNoUserBinding
import org.gaiateams.hospitalcovadonga.utils.areas
import org.gaiateams.hospitalcovadonga.utils.buildResumeDialog
import org.gaiateams.hospitalcovadonga.utils.df
import org.gaiateams.hospitalcovadonga.utils.setHora

class CitationNoUserFragment : Fragment() {
    private var _binding: FragmentCitationNoUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitationNoUserBinding.inflate(inflater, container, false)

        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText(resources.getString(R.string.citation_hour))
                .build()
        timePicker.addOnPositiveButtonClickListener {
            binding.txtHour.setText(setHora(timePicker.hour.toString(), timePicker.minute.toString()))
        }

        binding.txtArea.setAdapter(ArrayAdapter(requireActivity(), R.layout.support_simple_spinner_dropdown_item, areas))

        binding.txtHour.setOnClickListener {
            timePicker.show((activity as AuthenticationActivity).supportFragmentManager, resources.getString(R.string.citation_hour))
        }

        binding.bttnCreateCitation.setOnClickListener {
            binding.bttnCreateCitation.setOnClickListener {
                buildResumeDialog(
                    context = requireActivity(),
                    title = R.string.resume_title,
                    day = df.format(binding.calendarView.date),
                    hour = binding.txtHour.text.toString(),
                    doctorId = 17,
                    doctorName = "Braulio Palagot",
                    doctorArea = binding.txtArea.text.toString(),
                    userName = binding.txtName.text.toString()
                )
                    .setPositiveButton(R.string.confirm) { dialog, which ->
                        (activity as AuthenticationActivity).onBackPressed()
                    }
                    .setNegativeButton(R.string.edit) { dialog, which ->

                    }
                    .show()
            }
        }

        return binding.root
    }
}