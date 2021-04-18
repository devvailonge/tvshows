package com.devvailonge.home.domain


data class HomeState(
    val series: List<Series> = emptyList(),
    val reason: Throwable? = null,
    val syncState: HomeSyncState = HomeSyncState.SeriesIdle
) {
    fun reducer(
        series: List<Series> = emptyList(),
        reason: Throwable? = null,
        syncState: HomeSyncState? = null
    ): HomeState {
        return copy(
            series = series.takeIf { it.isNotEmpty() } ?: this.series,
            reason = reason ?: this.reason,
            syncState = syncState ?: this.syncState
        )
    }
}

sealed class HomeSyncState {
    object EmptySeries : HomeSyncState()
    object SeriesFailure : HomeSyncState()
    object SeriesSuccess : HomeSyncState()
    object SeriesLoading : HomeSyncState()
    object SeriesIdle : HomeSyncState()
}
