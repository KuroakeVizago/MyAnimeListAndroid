package com.kuroakevizago.anime_list_app.adapter

import android.content.ContentResolver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuroakevizago.anime_list_app.data.AnimeData
import com.kuroakevizago.anime_list_app.databinding.AnimeCardViewBinding
import com.kuroakevizago.anime_list_app.interfaces.OnItemClickListener

class AnimeListAdapter(
    private val animeList: ArrayList<AnimeData>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(val cardViewBinding: AnimeCardViewBinding) : RecyclerView.ViewHolder(cardViewBinding.root) {
        init {
            cardViewBinding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val cardViewBinding = AnimeCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(cardViewBinding)
    }

    override fun getItemCount(): Int = animeList.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val (name, description, imageUrl, genres, rating) = animeList[position]

        holder.cardViewBinding.tvCardName.text = name
        holder.cardViewBinding.tvCardDescription.text = description
        holder.cardViewBinding.ratingBar.rating = rating
        holder.cardViewBinding.tvGenreTags.text = genres
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.cardViewBinding.imgCard)
    }
}