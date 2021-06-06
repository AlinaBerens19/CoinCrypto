package com.example.mycrypto.presentation.ui.homeScreen



sealed class MarketsDataStateEvent {

    object NewSearchEvent : MarketsDataStateEvent()

    // restore after process death
    object RestoreStateEvent: MarketsDataStateEvent()
}