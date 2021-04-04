package com.example.imdbclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbclone.ClickInterface
import com.example.imdbclone.R
import com.example.imdbclone.databinding.MoviesItemBinding
import com.example.imdbclone.models.movies.Result
import com.example.imdbclone.utils.Utilities

class MoviesAdapter(private var list: List<Result>,private val clickInterface: ClickInterface) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
           init {
               view.setOnClickListener {
                   if(adapterPosition!=RecyclerView.NO_POSITION){
                       clickInterface.onClick(list[adapterPosition].id)
                   }
               }

           }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = MoviesItemBinding.bind(holder.itemView)
        Glide.with(holder.itemView).load("${Utilities.IMG_URL}${list[position].posterPath}")
            .into(binding.movieImage)
        binding.movieName.text = list[position].title
        binding.stars.text = list[position].voteAverage.toString()
    }

    override fun getItemCount() = list.size
}