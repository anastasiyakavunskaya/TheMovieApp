package com.example.topmovies.rest

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface TheMovieDBApi {

    @GET("3/discover/movie?api_key=66c7ba3274462ec38a7632e4b3b6da84")
    fun getInfoByYear(
        @Query("primary_release_year") year: Int = 2019,
        @Query("page") page: Int = 1
    ):  Observable<FilmList>


}