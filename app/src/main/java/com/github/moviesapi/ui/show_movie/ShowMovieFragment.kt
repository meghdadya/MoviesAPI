package com.github.moviesapi.ui.show_movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.github.moviesapi.databinding.FragmentMoviesBinding
import com.github.moviesapi.databinding.FragmentShowMovieBinding
import com.github.moviesapi.network.Result
import com.github.moviesapi.ui.MainViewModel
import com.github.moviesapi.utils.hideProgress
import com.github.moviesapi.utils.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ShowMovieFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentShowMovieBinding? = null
    val args: ShowMovieFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post {
            getMovie(args.movieId)
            binding.fragmentShowImage.transitionName = args.id
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun getMovie(id: Int) {
        viewModel.observeMovie(id).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    hideProgress()
                    binding.model = it.data
                }
                Result.Status.ERROR -> {
                    hideProgress()
                }
                Result.Status.LOADING -> showProgress()
            }

        })
    }
}