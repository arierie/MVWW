package id.arieridwan.mvww.data.network.response

import com.squareup.moshi.Json

class MovieResponse (val title: String, @Json(name = "poster_path") val posterPath: String, val overview: String,
                     @Json(name = "backdrop_path") val backdropPath: String, @Json(name = "release_date") val releaseDate: String,
                     val id: Int = 0, val video: Boolean = false, @Json(name = "vote_average") val voteAverage: Double = 0.toDouble(),
                     val popularity: Double = 0.toDouble())
