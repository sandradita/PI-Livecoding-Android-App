package com.postindustria.livecodingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.postindustria.livecodingapp.databinding.ItemMovieBinding
import com.postindustria.livecodingapp.model.Movie


class MovieAdapter(
    private val onClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(Movie.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }
}


class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onClick: (Movie) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: Movie

    init {
        itemView.setOnClickListener {
            onClick(item)
        }
    }

    fun bindItem(movie: Movie) = with(binding) {
        item = movie
        tvMovieTitle.text = movie.title

        Glide.with(ivMoviePreview)
            .load(movie.image)
            .into(ivMoviePreview)
    }

}