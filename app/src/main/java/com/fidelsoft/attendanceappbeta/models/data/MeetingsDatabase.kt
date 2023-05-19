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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fidelsoft.attendanceappbeta.models.MeetingModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


/**
 * The Room database that contains the Attendees table and the meetings table
 */

@Database (entities = [MeetingModel::class] ,version = 1)
abstract class MeetingsDatabase : RoomDatabase() {

    abstract fun meetingsDao(): MeetingsDao

    companion object {
        @Volatile
        private var INSTANCE: MeetingsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ) : MeetingsDatabase {

            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MeetingsDatabase::class.java,
                    "meetings_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(MeetingDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class MeetingDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.meetingsDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(meetingDao: MeetingsDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            meetingDao.deleteAllMeetings()

            var meeting = MeetingModel(
                UUID.randomUUID().toString(),
                "Kotlin with modes",
            "12/12/2023",
            "OutBox, kampala")
            meetingDao.insertMeeting(meeting)

        }

    }
}


