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

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.fidelsoft.attendanceappbeta.models.MeetingModel
import com.fidelsoft.attendanceappbeta.repositories.MeetingRepository

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all attendees.
 */

class AttendanceListingViewModel(private val repository: MeetingRepository) :
    ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMeetings: LiveData<List<MeetingModel>> =
        repository.allMeetings.asLiveData()
}

class AttendanceListingViewModelFactory(val repository: MeetingRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttendanceListingViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return  AttendanceListingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}