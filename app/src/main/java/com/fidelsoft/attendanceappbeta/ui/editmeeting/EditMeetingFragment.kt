package com.fidelsoft.attendanceappbeta.ui.editmeeting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.fidelsoft.attendanceappbeta.ui.meeting.ID
import com.fidelsoft.attendanceappbeta.ui.meeting.MeetingViewModel
import com.fidelsoft.attendanceappbeta.ui.meeting.MeetingViewModelFactory

class EditMeetingFragment : Fragment() {

    //view objects
    lateinit var editMeetingTitle : EditText
    lateinit var editMeetingDate : EditText
    lateinit var editMeetingLocation : EditText

    lateinit var meetingID: String

    companion object {
        fun newInstance() = EditMeetingFragment()
        private const val TAG = "meetingEditItemFragment"
    }

    private val meetingViewModel: MeetingViewModel by viewModels {
        MeetingViewModelFactory((this.activity?.application as MeetingsApplication).repository)
    }

    lateinit var buttonSave : Button
    private lateinit var viewModel: EditMeetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_meeting, container, false)

        editMeetingTitle = view.findViewById<EditText>(R.id.edtvEditMeetingTitle)
        editMeetingDate = view.findViewById<EditText>(R.id.edtvEditMeetingDate)
        editMeetingLocation = view.findViewById<EditText>(R.id.edtvEditMeetingLocation)

        //populate view with selected items id
        activity?.supportFragmentManager?.setFragmentResultListener(ID,this,) {id, bundle ->
            meetingID = bundle.getString("meeting_id").toString()
            var meetingTitle = bundle.getString("meeting_title")
            var meetingDate = bundle.getString("meeting_date")
            var meetingLocation = bundle.getString("meeting_location")

            editMeetingTitle.setText(meetingTitle)
            editMeetingDate.setText(meetingDate)
            editMeetingLocation.setText(meetingLocation)

            //save button click
            val buttonEdit = view.findViewById<Button>(R.id.buttonSaveEditMeeting)
            buttonEdit.setOnClickListener{
                editAttendance(view)
            }
        }

        return view
    }

    private fun editAttendance(view: View) {
        //Edit text save edit text changes
        // Use the Kotlin extension in the fragment-ktx artifact
        if(meetingID != null ){
            val title = editMeetingTitle.text.toString()
            val date = editMeetingDate.text.toString()
            val location = editMeetingLocation.text.toString()

            //add editable new text
            val editmeeting = MeetingModel(
                meetingID,
                title,
                date,
                location
            )

             meetingViewModel.update(editmeeting)


            //
            Toast.makeText(
                activity?.applicationContext,
                "Edit Success",
                Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditMeetingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}