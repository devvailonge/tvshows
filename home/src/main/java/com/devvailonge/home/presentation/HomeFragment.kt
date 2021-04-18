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
    private var filmList = getFilms()
    private val adapter = CategoryAdapter(::categoryClickListener)
    private val adapterFilm = FilmAdapter(filmList)

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
            }
        }
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


    private fun getFilms(): List<Film> {
        return arrayListOf(

            Film(
                "The Batman",
                "https://i.pinimg.com/originals/c9/fe/b1/c9feb142ee2ac8d3c184ae58bd533f35.png"),

            Film(
                "Fast & Furious",
                "https://static.cinecitta.de/portal/pics/berechnet/portal/pics/bilderdb/2021-04-01/2a0d318d-2cec-48dd-abeb-e4184105217c-_-_-141_Nz7jxgSmC7HuEouPhztWAwddEf8QFHluhCqMnrgUB4ShzjbVJ9nRd_L9NWvMqQ1vmfZxNpuYrUWtXXh_iw...jpg"),
            Film(
                "James Bond: No Time To Die",
                "https://i.pinimg.com/originals/29/e7/23/29e7231164c94243f66e555c875ef3cb.jpg"),
            Film(
                "Top Gun 2",
                "https://upload.wikimedia.org/wikipedia/en/thumb/d/d2/Top_Gun_Maverick.jpg/220px-Top_Gun_Maverick.jpg"),
            Film("Dune", "https://images-na.ssl-images-amazon.com/images/I/81A2o613bOL._RI_.jpg"),
            Film("Justice League", "https://crops.giga.de/2f/b0/bc/602b1aafad6997a67dc9be3582_YyAxNDE5eDc5OCs4KzU2AnJlIDUwMCAyODADMjA2YmFkOTZjZTg=.jpg"),
            Film("Black Mirror", ""),
            Film("The King's Man", ""),
            Film("Black Adam", ""),
            Film("The Suicide Squad", ""),
            Film("Rumble", ""),
            Film("The Little Things", ""),


            ).toList()
    }


    data class Film(val nome: String, val image: String)
}



