package com.postindustria.livecodingapp.ui.seacrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.postindustria.livecodingapp.databinding.FragmentSavedMoviesBinding
import com.postindustria.livecodingapp.databinding.FragmentSearchBinding
import com.postindustria.livecodingapp.ui.adapter.MovieAdapter


class SearchMovieFragment: Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val adapter = MovieAdapter { movie ->
        findNavController().navigate(SearchMovieFragmentDirections.showFullMovie(movie))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearchQuery.setOnEditorActionListener { v, actionId, event ->
            val text = binding.etSearchQuery.text.toString()
            if (text.isNotBlank()) {
                viewModel.search(text)
            }
            true
        }

        binding.rvSearchResults.adapter = adapter

        viewModel.resultsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}