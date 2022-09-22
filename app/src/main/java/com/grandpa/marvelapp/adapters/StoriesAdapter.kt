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
import com.grandpa.marvelapp.model.dto.StoriesDto
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass

class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.MyViewHolder>() {

    var storiesListData = mutableListOf<StoriesDto>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tvStoriesName)
        private val tvDescription: TextView = view.findViewById(R.id.tvStoriesDescription)
        private val imageView: ImageView = view.findViewById(R.id.storiesThumbImageView)

        fun bind(data: StoriesDto) {
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
        return StoriesAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stories_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(storiesListData[position])
    }

    override fun getItemCount(): Int {
        return storiesListData.size
    }
}