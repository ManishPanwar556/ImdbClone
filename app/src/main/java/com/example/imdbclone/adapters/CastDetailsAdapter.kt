package com.example.imdbclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbclone.R
import com.example.imdbclone.databinding.ActivityMainBinding
import com.example.imdbclone.databinding.CastItemBinding
import com.example.imdbclone.models.persons.cast.Cast
import com.example.imdbclone.utils.Utilities

class CastDetailsAdapter(private val list: List<Cast>) :
    RecyclerView.Adapter<CastDetailsAdapter.CastViewHolder>() {
    inner class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cast_item, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val binding = CastItemBinding.bind(holder.itemView)
        binding.castName.text = list[position].name
        binding.characterName.text = list[position].character
        Glide.with(holder.itemView).load("${Utilities.IMG_URL}${list[position].profilePath}")
            .into(binding.castProfile)
    }

    override fun getItemCount() = list.size
}