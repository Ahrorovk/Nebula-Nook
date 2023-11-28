package com.ahrorovk.weatherapp.app.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(

): ViewModel(){
    fun onEvent(event: NavigationEvent){
        when(event){
            is NavigationEvent.GetWeather -> {
                event.state.invoke()
            }
        }
    }
}