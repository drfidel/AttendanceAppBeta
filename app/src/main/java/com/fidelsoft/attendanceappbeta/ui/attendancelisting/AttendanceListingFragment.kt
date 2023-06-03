/*
 * Copyright (C) 2023 The Attendee App Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fidelsoft.attendanceappbeta.ui.attendancelisting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fidelsoft.attendanceappbeta.R
import com.fidelsoft.attendanceappbeta.databinding.FragmentAttendanceListingBinding
import com.fidelsoft.attendanceappbeta.repositories.MeetingsApplication
import com.fidelsoft.attendanceappbeta.ui.meeting.MeetingViewModelFactory

const val ID_MEETING = "ID_MEETING"
class AttendanceListingFragment : Fragment() {

    private var _binding: FragmentAttendanceListingBinding? = null
    private val adapter = AttendanceListingAdapter()

    lateinit var recyclerView: RecyclerView

    private val attendanceListingViewModel: AttendanceListingViewModel by viewModels{
        AttendanceListingViewModelFactory((this.activity?.application as MeetingsApplication).repository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        attendanceListingViewModel.allMeetings.observe(this) { meetings ->
            // Update the cached copy of the meetings in the adapter.
            meetings.let { adapter.submitList(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_attendance_listing, container, false)

        //setup recycler view
        setupRecyclerView(view)

        return view
    }

    //setup recycler view
    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rvAttendanceListing)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL

        recyclerView.layoutManager = layoutManager

        //swipe right to delete

        //click listener to the adapter,
        // opens list of sorted attendances by meeting id
        adapter.setMyOnItemClickListener(object : AttendanceListingAdapter.rvItemClicked{
            override fun onItemClicked(position: Int) {
                Log.i(TAG, "$TAG RV click pressed$position")

                //query database and load data at position
            }
        })


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "MeetingFragment"
    }

}