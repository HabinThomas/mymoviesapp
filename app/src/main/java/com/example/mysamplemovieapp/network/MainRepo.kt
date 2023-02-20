package com.example.mysamplemovieapp.network

class MainRepo {

    suspend fun getMovieList() = ApiInstance.apiInterface.getMoviesList()

}