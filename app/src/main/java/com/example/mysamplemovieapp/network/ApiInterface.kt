package com.example.mysamplemovieapp.network

import com.example.mysamplemovieapp.dataClass.MoviesDataClassItem
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("759fdfa82d6f33522e11")
    suspend fun getMoviesList(): Response<ArrayList<MoviesDataClassItem>>

}
