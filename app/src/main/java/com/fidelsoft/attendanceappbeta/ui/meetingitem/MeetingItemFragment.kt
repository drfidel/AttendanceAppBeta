package com.fidelsoft.attendanceappbeta.ui.meetingitem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fidelsoft.attendanceappbeta.R
import com.fidelsoft.attendanceappbeta.models.MeetingModel
import com.fidelsoft.attendanceappbeta.repositories.MeetingsApplication
import com.fidelsoft.attendanceappbeta.ui.meeting.MeetingViewModel
import com.fidelsoft.attendanceappbeta.ui.meeting.MeetingViewModelFactory
import java.util.*

class MeetingItemFragment : Fragment() {

    //view objects
    lateinit var editMeetingTitle : EditText
    lateinit var editMeetingDate : EditText
    lateinit var editMeetingLocation : EditText


    private val meetingViewModel: MeetingViewModel by viewModels {
        MeetingViewModelFactory((this.activity?.application as MeetingsApplication).repository)
    }

    companion object {
        fun newInstance() = MeetingItemFragment()
        private const val TAG = "meetingItemFragment"
    }

    lateinit var buttonSave : Button
    private lateinit var viewModel: MeetingItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meetings_item, container, false)

        //save button click
        buttonSave = view.findViewById<Button>(R.id.buttonSaveMeeting)
        buttonSave.setOnClickListener{
            saveAttendance(view)
        }
        return view
    }

    private fun saveAttendance(view: View) {
        Log.i(TAG, "$TAG Save Button pressed")

        //all other fragment view to be added here
        editMeetingTitle = view.findViewById(R.id.edtvMeetingTitle)
        editMeetingDate = view.findViewById(R.id.edtvMeetingDate)
        editMeetingLocation = view.findViewById(R.id.edtvMeetingLocation)



        // Use the Kotlin extension in the fragment-ktx artifact
        if (TextUtils.isEmpty(editMeetingTitle.text) ||
            TextUtils.isEmpty(editMeetingDate.text)
        ) {
            Toast.makeText(
                this.context,
                "Please fill in the details",
                Toast.LENGTH_LONG
            ).show()
            editMeetingTitle.findFocus()
        } else {
            val title = editMeetingTitle.text.toString()
            val date = editMeetingDate.text.toString()
            val location = editMeetingLocation.text.toString()

            val meeting = MeetingModel(
                UUID.randomUUID().toString(),
                title,
                date,
                location
            )

            meetingViewModel.insert(meeting)

            //disable button
            buttonSave.isEnabled = false

            //
            Toast.makeText(
                activity?.applicationContext,
                "Save Success",
                Toast.LENGTH_SHORT
            ).show()

            //programaticall perform back navigation

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeetingItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}