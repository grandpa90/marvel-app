package com.grandpa.marvelapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grandpa.marvelapp.R
import com.grandpa.marvelapp.model.dto.CharacterDto

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    var characterListData = mutableListOf<CharacterDto>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        private val imageView: ImageView = view.findViewById(R.id.charThumbImageView)

        fun bind(data: CharacterDto) {
            tvName.text = data.name
            tvDescription.text = data.description
            val url = data.thumbnail
            Glide.with(imageView)
                .load(url)
                .circleCrop()
                .into(imageView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(characterListData[position])
    }

    override fun getItemCount(): Int {
        return characterListData.size
    }


}