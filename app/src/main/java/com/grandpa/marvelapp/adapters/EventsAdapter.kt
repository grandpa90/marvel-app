package com.grandpa.marvelapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grandpa.marvelapp.R
import com.grandpa.marvelapp.model.dto.EventsDto
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {

    var eventsDataList = mutableListOf<EventsDto>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tvEventsName)
        private val tvDescription: TextView = view.findViewById(R.id.tvEventsDescription)
        private val imageView: ImageView = view.findViewById(R.id.eventsThumbImageView)

        fun bind(data: EventsDto) {
            tvName.text = data.title
            tvDescription.text = data.description
            val url = data.thumbnail
            Log.wtf("ULR", url)
//            Picasso.with(context).load(url).into(imageView)

            Glide.with(MarvelAppApplicationClass.context)
                .load(url)
                .circleCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return EventsAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.events_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(eventsDataList[position])
    }

    override fun getItemCount(): Int {
        return eventsDataList.size
    }
}