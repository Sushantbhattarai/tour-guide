package com.example.nit3213

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val destination: String,
    val country: String,
    val bestSeason: String,
    val popularAttraction: String,
    val description: String
) : Parcelable
