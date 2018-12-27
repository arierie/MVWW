package id.arieridwan.mvww.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by arieridwan on 27/12/18.
 */

@Entity
data class Movie(val title: String, val posterPath: String, val overview: String,
                 val backdropPath: String, val releaseDate: String,
                 @PrimaryKey(autoGenerate = false) val id: Int = 0,
                 val video: Boolean = false, val voteAverage: Double = 0.toDouble(),
                 val popularity: Double = 0.toDouble())