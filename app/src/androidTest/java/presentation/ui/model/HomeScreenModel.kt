package com.example.coingechkoproject.presentation.ui.model

import com.google.gson.annotations.SerializedName


data class HomeScreenModel(

    var id: List<String>,

    var image: List<String>,

    var current_price: List<Double>,

    var total_volume: List<Double>

)