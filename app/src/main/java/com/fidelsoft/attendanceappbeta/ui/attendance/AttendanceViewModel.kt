package com.fidelsoft.attendanceappbeta.ui.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AttendanceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is attendance Fragment"
    }
    val text: LiveData<String> = _text
}