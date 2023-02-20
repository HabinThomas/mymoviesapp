package com.example.mysamplemovieapp.helper

import com.example.mysamplemovieapp.dataClass.MoviesDataClassItem
import java.util.*

class FilterHelper {

    fun search(
        searchedText: String,
        originalMoviesList: ArrayList<MoviesDataClassItem>
    ): ArrayList<MoviesDataClassItem> {
        val filterMoviesList = ArrayList<MoviesDataClassItem>()
        return if (searchedText.isEmpty()) {
            originalMoviesList
        } else {
            for (movie in originalMoviesList) {
                if (movie.Title.lowercase(Locale.getDefault()).contains(
                        searchedText.lowercase(
                            Locale.getDefault()
                        )
                    ) || movie.Genre.lowercase(Locale.getDefault()).contains(
                        searchedText.lowercase(
                            Locale.getDefault()
                        )
                    )
                ) {
                    filterMoviesList.add(movie)
                }
            }
            filterMoviesList
        }
    }
}