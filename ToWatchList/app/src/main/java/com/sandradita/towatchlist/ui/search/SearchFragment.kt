package com.sandradita.towatchlist.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sandradita.towatchlist.R
import com.sandradita.towatchlist.databinding.FragmentSearchBinding
import com.sandradita.towatchlist.models.Movie
import com.sandradita.towatchlist.models.SearchResult
import com.sandradita.towatchlist.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    private val viewModel: SearchViewModel by viewModels()

    private val adapter = MovieAdapter(::onMovieClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()

        viewModel.resultLiveData.observe(viewLifecycleOwner, ::bindResult)

        binding?.etSearchQuery?.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val text = v.text?.toString()
                    if (!text.isNullOrBlank()) {
                        hideKeyboard()
                        binding?.progressBar?.isVisible = true
                        viewModel.search(text)
                    }
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun hideKeyboard() {
        val rootView = binding?.root ?: return

        val imm = activity?.getSystemService<InputMethodManager>()
        imm?.hideSoftInputFromWindow(rootView.windowToken, 0)
    }

    private fun setupList() {
        binding?.rvSearchResults?.apply {
            adapter = this@SearchFragment.adapter
        }
    }

    private fun bindResult(searchResult: SearchResult) = binding?.apply {
        progressBar.isVisible = false
        adapter.submitList(searchResult.results)
        if (!searchResult.errorMessage.isNullOrEmpty()) {
            Snackbar.make(root, searchResult.errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun onMovieClicked(movie: Movie) {
        findNavController().navigate(SearchFragmentDirections.showFullInfo(movie))
    }
}