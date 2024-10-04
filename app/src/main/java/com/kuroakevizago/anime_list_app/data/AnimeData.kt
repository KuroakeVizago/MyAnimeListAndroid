package com.kuroakevizago.anime_list_app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeData(val name: String, val description: String, val imageUrl: String, val genres : String, val rating: Float) : Parcelable