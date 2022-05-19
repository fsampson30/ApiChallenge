package com.sampson.apichallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampson.apichallenge.DateUtils
import com.sampson.apichallenge.R
import com.sampson.apichallenge.model.Events

class EventsAdapter(
    private val context: Context,
    private val clickListener: EventClickListener
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>(){

    private var eventsList = listOf<Events>()

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtEventTitle: TextView = itemView.findViewById(R.id.txtCardShowEventTitle)
        val txtEventDate: TextView = itemView.findViewById(R.id.txtCardShowEventDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.event_card, parent,false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.txtEventTitle.text = eventsList[position].title
        holder.txtEventDate.text = DateUtils.convertMiliToDate(eventsList[position].date)
        holder.itemView.setOnClickListener { clickListener.onEventClick(eventsList[position]) }
    }

    override fun getItemCount() = eventsList.size

    fun submitList(events: List<Events>){
        this.eventsList = events
        notifyDataSetChanged()
    }

    interface EventClickListener {
        fun onEventClick(event: Events)
    }
}


