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

package com.fidelsoft.attendanceappbeta.models.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fidelsoft.attendanceappbeta.models.MeetingModel
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the attendees table.
 **/
@Dao
interface MeetingsDao {
    /**
     * Get list of all meetings.
     * @return all meetings from the table.
     */
    @Query("SELECT * FROM `meetings-table`")
    fun getAllMeetings(): Flow<List<MeetingModel>>

    /**
     * Get a meeting by id.
     * @return the meeting from the table with a specific id.
     */
    @Query("SELECT * FROM `meetings-table` WHERE meetingId = :id")
    fun getMeetingById(id: String): Flow<List<MeetingModel>>


    /**
     * Insert a meeting into the database. If the meeting already exists, don't replace it.
     * @param add the meeting to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMeeting(meeting: MeetingModel)

    //update meeting
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMeeting(attendee: MeetingModel)

    /**
     * Delete meeting by ID.
     */
    @Query("DELETE FROM `meetings-table` WHERE meetingId = :meetid")
    suspend fun deletebyID(meetid: String)


    /**
     * Delete all meetings.
     */
    @Query("DELETE FROM `meetings-table`")
    suspend fun deleteAllMeetings()
}

