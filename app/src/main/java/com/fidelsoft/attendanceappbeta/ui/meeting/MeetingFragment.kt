package com.fidelsoft.attendanceappbeta.ui.meeting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fidelsoft.attendanceappbeta.databinding.FragmentMeetingsBinding

class MeetingFragment : Fragment() {

    private var _binding: FragmentMeetingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val meetingViewModel =
            ViewModelProvider(this).get(MeetingViewModel::class.java)

        _binding = FragmentMeetingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMeeting
        meetingViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}