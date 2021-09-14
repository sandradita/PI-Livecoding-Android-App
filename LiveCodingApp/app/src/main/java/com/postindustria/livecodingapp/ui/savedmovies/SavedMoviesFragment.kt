package com.postindustria.livecodingapp.ui.savedmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.postindustria.livecodingapp.R
import com.postindustria.livecodingapp.databinding.FragmentSavedMoviesBinding
import com.postindustria.livecodingapp.ui.adapter.MovieAdapter
import com.postindustria.livecodingapp.ui.seacrh.SearchMovieFragmentDirections


class SavedMoviesFragment : Fragment() {

    private var _binding: FragmentSavedMoviesBinding? = null
    private val binding: FragmentSavedMoviesBinding
        get() = _binding!!

    private val viewModel: SaveMovieViewModel by viewModels()

    private val adapter = MovieAdapter { movie ->
        findNavController().navigate(SearchMovieFragmentDirections.showFullMovie(movie))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShowSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        binding.rvSavedMovies.adapter = adapter

        viewModel.saveMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}