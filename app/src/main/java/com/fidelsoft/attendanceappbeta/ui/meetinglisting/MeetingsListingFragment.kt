package com.fidelsoft.attendanceappbeta.ui.meetinglisting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fidelsoft.attendanceappbeta.R

class MeetingsListingFragment : Fragment() {

    companion object {
        fun newInstance() = MeetingsListingFragment()
    }

    private lateinit var viewModel: MeetingsListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meetings_listing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeetingsListingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}