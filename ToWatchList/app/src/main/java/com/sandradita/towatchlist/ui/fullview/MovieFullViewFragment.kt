package com.sandradita.towatchlist.ui.fullview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sandradita.towatchlist.R
import com.sandradita.towatchlist.databinding.FragmentFullViewBinding
import com.sandradita.towatchlist.models.MovieFullInfo
import com.sandradita.towatchlist.models.Movie
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFullViewFragment : Fragment() {

    private var binding: FragmentFullViewBinding? = null

    private val viewModel: MovieFullViewModel by viewModels()
    private val args: MovieFullViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fabToggleSave?.setOnClickListener {
            viewModel.toggleSave()
        }

        bindMovie(args.movie)

        viewModel.fullInfoLiveData.observe(viewLifecycleOwner, ::bindFullMovieInfo)
        viewModel.existsLiveData.observe(viewLifecycleOwner, ::bindExists)

        if (!viewModel.isCached) {
            viewModel.reloadInfo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun bindExists(exists: Boolean) {
        if (exists) {
            binding?.fabToggleSave?.setText(R.string.btn_remove_from_watch)
        } else {
            binding?.fabToggleSave?.setText(R.string.btn_save_to_watch)
        }
    }

    private fun bindMovie(movie: Movie) = binding?.apply {
        Glide.with(ivMovieImage)
            .load(movie.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_baseline_movie_24)
            .into(ivMovieImage)
        tvMovieTitle.text = movie.title
    }

    private fun bindFullMovieInfo(movie: MovieFullInfo?) = binding?.apply {
        if (movie == null) return@apply

        progressBar.isVisible = false
        tvMovieTitle.text = movie.title
        tvMovieYear.text = movie.year.toString()
        tvMovieDirector.text = movie.directors
        tvMovieGenres.text = movie.genres
        tvMovieStarts.text = movie.stars
        tvMovieRating.text = movie.imDbRating
        tvMoviePlot.text = movie.plotLocal
    }

}