package com.github.moviesapi.ui.movies_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Query
import com.github.moviesapi.databinding.FragmentMoviesBinding
import com.github.moviesapi.ui.MainViewModel
import com.github.moviesapi.utils.hideToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMoviesBinding? = null
    lateinit var mainListAdapter: MainListAdapter

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post {
            setupList()
            setupView()
            binding.fragmentMoviesSearchBox.doAfterTextChanged { text ->
                text?.let {
                    if (it.length > 3) {
                        clearList()
                        searchMovie(it.toString())

                    } else {
                        if (it.isEmpty()) {
                            clearList()
                            setupView()
                        }
                    }
                }
            }

        }
    }

    private fun searchMovie(query: String) {
        lifecycleScope.launch {
            viewModel.observeSearchMovies(query).collect {
                mainListAdapter.submitData(it)
                setupList()
            }
        }
    }

    private fun clearList() {
        mainListAdapter.submitData(lifecycle, PagingData.empty())
    }

    private fun setupView() {
        lifecycleScope.launch {
            viewModel.observeDiscoverMovies().collect {
                mainListAdapter.submitData(it)

            }
        }
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter(onClickListener = { view, item ->
            navigateToShowMovie(view, item.id!!)
        })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToShowMovie(view: View, id: Int) {
        view.let {
            val extras = FragmentNavigatorExtras(
                view to id.toString()
            )
            val action =
                MoviesFragmentDirections.actionMoviesFragmentToShowMovieFragment(
                    id, id.toString()
                )
            findNavController().navigate(action, extras)
        }
    }

}