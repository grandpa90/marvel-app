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
import com.grandpa.marvelapp.model.dto.CharacterDto
import com.grandpa.marvelapp.utils.MarvelAppApplicationClass.Companion.context

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(postion: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    var characterListData = mutableListOf<CharacterDto>()

    class MyViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {

        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        private val imageView: ImageView = view.findViewById(R.id.charThumbImageView)


        init {
            view.setOnClickListener {
                listener.onItemClick(postion = adapterPosition)
            }

        }

        fun bind(data: CharacterDto) {
            tvName.text = data.name
            tvDescription.text = data.description
            val url = data.thumbnail
            Log.wtf("ULR", url)
//            Picasso.with(context).load(url).into(imageView)

            Glide.with(context)
                .load(url)
                .circleCrop()
                .into(imageView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false),
            mListener
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(characterListData[position])
    }

    override fun getItemCount(): Int {
        return characterListData.size
    }


}