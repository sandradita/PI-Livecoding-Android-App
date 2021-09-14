package com.sandradita.towatchlist.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Movie(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val image: String,
    val resultType: SearchType
) : Parcelable {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}


@Entity
data class MovieFullInfo(
    @PrimaryKey val id: String,
    val title: String,
    val year: Int,
    val image: String,
    val directors: String,
    val stars: String,
    val genres: String?,
    val imDbRating: String?,
    val plotLocal: String?
)