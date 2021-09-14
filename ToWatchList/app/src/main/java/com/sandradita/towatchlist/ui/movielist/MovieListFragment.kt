package com.sandradita.towatchlist.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sandradita.towatchlist.R
import com.sandradita.towatchlist.databinding.FragmentMovieListBinding
import com.sandradita.towatchlist.models.Movie
import com.sandradita.towatchlist.models.SearchResult
import com.sandradita.towatchlist.ui.adapter.MovieAdapter
import com.sandradita.towatchlist.ui.search.SearchFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var binding: FragmentMovieListBinding? = null

    private val viewModel: MovieListViewModel by viewModels()

    private val adapter = MovieAdapter(::onMovieClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()

        viewModel.savedMoviesLiveData.observe(viewLifecycleOwner, ::bindResult)

        binding?.fabAddMovie?.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupList() {
        binding?.rvMovieList?.apply {
            adapter = this@MovieListFragment.adapter
        }
    }

    private fun bindResult(list: List<Movie>) {
        adapter.submitList(list)
        binding?.tvNoMovies?.isVisible = list.isEmpty()
    }

    private fun onMovieClicked(movie: Movie) {
        findNavController().navigate(MovieListFragmentDirections.showFullInfo(movie))
    }

}