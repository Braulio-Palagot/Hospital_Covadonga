package org.gaiateams.hospitalcovadonga.ui.mainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.gaiateams.hospitalcovadonga.MainActivity
import org.gaiateams.hospitalcovadonga.R
import org.gaiateams.hospitalcovadonga.databinding.FragmentCitationWithUserBinding
import org.gaiateams.hospitalcovadonga.utils.areas
import org.gaiateams.hospitalcovadonga.utils.buildResumeDialog
import org.gaiateams.hospitalcovadonga.utils.df
import org.gaiateams.hospitalcovadonga.utils.setHora

class CitationWithUserFragment : Fragment() {
    private var _binding: FragmentCitationWithUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitationWithUserBinding.inflate(inflater, container, false)

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
            timePicker.show((activity as MainActivity).supportFragmentManager, resources.getString(R.string.citation_hour))
        }

        binding.bttnCreateCitation.setOnClickListener {
            buildResumeDialog(
                context = requireActivity(),
                title = R.string.resume_title,
                day = df.format(binding.calendarView.date),
                hour = binding.txtHour.text.toString(),
                doctorId = 17,
                doctorName = "Braulio Palagot",
                doctorArea = binding.txtArea.text.toString(),
                userName = "Claudia Ramírez"
            )
                .setPositiveButton(R.string.confirm) { dialog, which ->
                    (activity as MainActivity).onBackPressed()
                }
                .setNegativeButton(R.string.edit) { dialog, which ->

                }
                .show()
        }

        return binding.root
    }
}