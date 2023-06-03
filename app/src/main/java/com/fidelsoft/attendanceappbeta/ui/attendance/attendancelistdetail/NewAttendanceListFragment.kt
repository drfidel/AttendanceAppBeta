package com.fidelsoft.attendanceappbeta.ui.attendance.attendancelistdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fidelsoft.attendanceappbeta.R

class NewAttendanceListFragment : Fragment() {

    companion object {
        fun newInstance() = NewAttendanceListFragment()
    }

    private lateinit var viewModel: NewAttendanceListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_attendance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewAttendanceListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}