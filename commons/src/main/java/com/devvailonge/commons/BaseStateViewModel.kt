package com.devvailonge.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
abstract class BaseStateViewModel<State, Events>(private val initialState: State) : ViewModel() {

    //producer
    private val events = MutableSharedFlow<Events>()

    //consumer
    val state: StateFlow<State> = toState()

    private fun toState(): StateFlow<State> {
        return events
            .flatMapLatest { process(it) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, initialState)
    }

    abstract fun process(event: Events): Flow<State>

    fun dispatch(event: Events) {
        viewModelScope.launch { events.emit(event) }
    }
}