package com.postindustria.livecodingapp.ui.fullview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.postindustria.livecodingapp.R
import com.postindustria.livecodingapp.databinding.FragmentFullViewBinding
import com.postindustria.livecodingapp.databinding.FragmentSavedMoviesBinding


class FullViewFragment: Fragment() {

    private var _binding: FragmentFullViewBinding? = null
    private val binding: FragmentFullViewBinding
        get() = _binding!!

    private val args: FullViewFragmentArgs by navArgs()
    private val viewModel: FullViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullViewBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMovieTitle.text = args.movie.title

        Glide.with(binding.ivMoviePreview)
            .load(args.movie.image)
            .into(binding.ivMoviePreview)

        val isExists = viewModel.isExists(args.movie.id)
        isExists.observe(viewLifecycleOwner) { isExists ->
            if (isExists) {
                binding.btnSave.setText(R.string.remove_local)
            } else {
                binding.btnSave.setText(R.string.save_to_local)
            }
        }

        binding.btnSave.setOnClickListener {
            if (isExists.value == true) {
                viewModel.delete(args.movie)
            } else {
                viewModel.save(args.movie)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}