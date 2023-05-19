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
package com.fidelsoft.attendanceappbeta.repositories

import androidx.annotation.WorkerThread
import com.fidelsoft.attendanceappbeta.models.MeetingModel
import com.fidelsoft.attendanceappbeta.models.data.MeetingsDao

import kotlinx.coroutines.flow.Flow

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class MeetingRepository ( private val meetingsDao: MeetingsDao){
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMeetings: Flow<List<MeetingModel>> = meetingsDao.getAllMeetings()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(meeting: MeetingModel) {
        meetingsDao.insertMeeting(meeting)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(meeting: MeetingModel) {
        meetingsDao.updateMeeting(meeting)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteByID(meeting: String) {
        meetingsDao.deletebyID(meeting)
    }

}