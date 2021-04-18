package com.devvailonge.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devvailonge.commons.collectIn
import com.devvailonge.commons.viewBinding
import com.devvailonge.home.R
import com.devvailonge.home.databinding.FragmentHomeBinding
import com.devvailonge.home.domain.HomeState
import com.devvailonge.home.domain.HomeSyncState
import com.devvailonge.home.domain.Series
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private var categoryList = getCategories()
    private val adapter = CategoryAdapter(::categoryClickListener)
    private val adapterFilm = FilmAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel
            .state
            .onEach { state -> renderState(state) }
            .collectIn(this)
    }

    private fun renderState(state: HomeState) {
        Timber.i("UI State: $state")
        when (state.syncState) {
            HomeSyncState.EmptySeries -> {
            }
            HomeSyncState.SeriesFailure -> {
            }
            HomeSyncState.SeriesIdle -> {
            }
            HomeSyncState.SeriesLoading -> {
            }
            HomeSyncState.SeriesSuccess -> {
                handleSuccess(state.series)
            }
        }
    }

    private fun handleSuccess(series: List<Series>) {
        adapterFilm.submitList(series)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCategories.adapter = adapter
        binding.rvFilms.adapter = adapterFilm
        adapter.updateList(categoryList)
    }

    private fun categoryClickListener(category: Category) {
        val newList = categoryList.map {
            if (it.category == category.category) {
                it.copy(isSelected = category.isSelected)
            } else {
                it
            }
        }
        categoryList = newList
        adapter.updateList(categoryList)
    }

    private fun getCategories(): List<Category> {
        return arrayListOf(
            Category("Action"),
            Category("Comedy"),
            Category("Crime"),
            Category("Adventure"),
            Category("Fantasy"),
            Category("Science Fiction"),
            Category("Suspense"),
            Category("Drama"),
            Category("Thriller"),
            Category("Horror"),
            Category("Romance"),
            Category("Children"),
            Category("Documentary"),
            Category("Family")
        ).toList()
    }

    data class Category(val category: String, var isSelected: Boolean = false)

}



