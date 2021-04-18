package com.devvailonge.home.presentation

import com.devvailonge.commons.BaseStateViewModel
import com.devvailonge.home.domain.HomeEvents
import com.devvailonge.home.domain.HomeState
import com.devvailonge.home.domain.HomeSyncState.SeriesLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchSeriesUseCase: FetchSeriesUseCase
) : BaseStateViewModel<HomeState, HomeEvents>(HomeState(syncState = SeriesLoading)) {

    init {
        dispatch(HomeEvents.Fetch)
    }

    override fun process(event: HomeEvents): Flow<HomeState> {
        return when (event) {
            HomeEvents.Fetch -> fetchSeriesUseCase.perform()
        }
    }

/*    //producer
    private val events = MutableSharedFlow<HomeEvents>()

    //consumer
    val state: StateFlow<HomeState> = toState()

    private fun toState(): StateFlow<HomeState> {
        return events
            .flatMapLatest { process(it) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, HomeState(syncState = SeriesLoading))
    }

    private fun process(event: HomeEvents): Flow<HomeState> {
        return when (event) {
            HomeEvents.Fetch -> fetchSeriesUseCase.perform()
        }
    }

    fun dispatch(event: HomeEvents) {
        viewModelScope.launch { events.emit(event) }
    }*/
}