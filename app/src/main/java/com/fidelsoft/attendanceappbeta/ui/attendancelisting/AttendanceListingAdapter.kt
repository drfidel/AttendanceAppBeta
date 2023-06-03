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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fidelsoft.attendanceappbeta.R
import com.fidelsoft.attendanceappbeta.models.MeetingModel

class AttendanceListingAdapter() :
    ListAdapter<
            MeetingModel,
            AttendanceListingAdapter.AttendanceListingViewHolder>(ATTENDANCE_LISTING_COMPARATOR){

    private lateinit var algListener : rvItemClicked

    interface rvItemClicked{
        fun onItemClicked(position: Int)
    }

    fun setMyOnItemClickListener(listener: rvItemClicked){
        algListener = listener
    }

        inner class AttendanceListingViewHolder(
            itemView: View, listener: rvItemClicked) :
        RecyclerView.ViewHolder(itemView){
            //List of views
            private val meetingIDItemView: TextView = itemView.findViewById(R.id.tvMeetingIDAttListing)
            private val meetingTitleItemView: TextView = itemView.findViewById(R.id.tvMeetingTitleAttListing)
            private val meetingDateItemView: TextView = itemView.findViewById(R.id.tvMeetingDateAttListing)
            private val meetingLocationItemView: TextView = itemView.findViewById(R.id.tvMeetingLocationAttListing)

            init {
                itemView.setOnClickListener{
                    listener.onItemClicked(adapterPosition)
                }
        }

            fun bind(
                meetingID:String?,
                meetingTitle: String?,
                meetingDate:String?,
                meetingLocation:String?){

                meetingIDItemView.text = meetingID
                meetingTitleItemView.text =  meetingTitle
                meetingDateItemView.text = meetingDate
                meetingLocationItemView.text = meetingLocation
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            AttendanceListingViewHolder{

            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.rvattendancelistingitem, parent, false)
            return AttendanceListingViewHolder(view, algListener)
        }

        override fun onBindViewHolder(holder: AttendanceListingViewHolder,position: Int) {
            val current = getItem(position)
            holder.bind(
                current.id,
                current.meetingTitle,
                current.meetingDate,
                current.meetingLocation
            )
        }

    companion object{
        private val ATTENDANCE_LISTING_COMPARATOR = object  : DiffUtil.ItemCallback<MeetingModel>(){
            override fun areContentsTheSame(oldItem: MeetingModel, newItem: MeetingModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: MeetingModel, newItem: MeetingModel): Boolean {
                return oldItem === newItem
            }
        }
    }


}
