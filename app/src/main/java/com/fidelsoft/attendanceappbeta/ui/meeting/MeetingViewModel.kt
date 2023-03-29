package com.fidelsoft.attendanceappbeta.ui.meeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeetingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is meetings Fragment"
    }
    val text: LiveData<String> = _text
}