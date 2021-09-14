package com.sandradita.towatchlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sandradita.towatchlist.R
import com.sandradita.towatchlist.databinding.ItemMovieBinding
import com.sandradita.towatchlist.models.Movie


class MovieAdapter(
    private val itemCallback: (Movie) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(Movie.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, itemCallback)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class MovieViewHolder(
    binding: ItemMovieBinding,
    itemCallback: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val title = binding.tvMovieTitle
    private val image = binding.ivMovieImage

    private var item: Movie? = null

    init {
        itemView.setOnClickListener {
            item?.let(itemCallback)
        }
    }

    fun bind(movie: Movie) {
        this.item = movie

        title.text = movie.title
        Glide.with(image)
            .load(movie.image)
            .override(200)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_baseline_movie_24)
            .into(image)
    }

}