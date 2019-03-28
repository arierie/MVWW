package id.arieridwan.mvww.data.disk.entity

import androidx.room.Entity

/**
 * Created by arieridwan on 27/12/18.
 */

@Entity(primaryKeys = [("id")])
data class Movie(val title: String, val posterPath: String, val overview: String,
                 val backdropPath: String, val releaseDate: String,
                 val id: Int,
                 val video: Boolean = false, val voteAverage: Double,
                 val popularity: Double)