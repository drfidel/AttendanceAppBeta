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

package com.fidelsoft.attendanceappbeta.ui.meeting

import androidx.lifecycle.*
import com.fidelsoft.attendanceappbeta.models.MeetingModel
import com.fidelsoft.attendanceappbeta.repositories.MeetingRepository
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all attendees.
 */
class MeetingViewModel(private val repository: MeetingRepository) :
    ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMeetings: LiveData<List<MeetingModel>> =
        repository.allMeetings.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(meeting: MeetingModel) = viewModelScope.launch {
        repository.insert(meeting)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun update(meeting: MeetingModel) = viewModelScope.launch {
        repository.update(meeting)
    }

    fun deleteID(meeting: String) = viewModelScope.launch {
        repository.deleteByID(meeting)
    }

}

class MeetingViewModelFactory(val repository: MeetingRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeetingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MeetingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
