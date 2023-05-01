package com.fidelsoft.attendanceappbeta.ui.attendancelisting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fidelsoft.attendanceappbeta.R

class AttendanceListingFragment : Fragment() {

    companion object {
        fun newInstance() = AttendanceListingFragment()
    }

    private lateinit var viewModel: AttendanceListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attendance_listing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AttendanceListingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}