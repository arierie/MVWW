package id.arieridwan.mvww.data.network.service

import id.arieridwan.mvww.data.network.response.MovieListResponse
import id.arieridwan.mvww.data.network.response.ReviewListResponse
import id.arieridwan.mvww.data.network.response.TrailerListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by arieridwan on 20/12/18.
 */

interface ApiService {

    @GET("movie/{category}")
    fun getMovies(@Path("category") category: String, @Query("api_key") apiKey: String,
                  @Query("page") page: Int): Observable<MovieListResponse>

    @GET("movie/{id}/videos")
    fun getTrailer(@Query("api_key") apiKey: String, @Path("id") id: Int): Observable<TrailerListResponse>

    @GET("movie/{id}/reviews")
    fun getReviews(@Query("api_key") apiKey: String, @Path("id") id: Int): Observable<ReviewListResponse>

}