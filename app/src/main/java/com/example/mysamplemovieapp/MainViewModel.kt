package com.example.mysamplemovieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysamplemovieapp.dataClass.MoviesDataClassItem
import com.example.mysamplemovieapp.helper.FilterHelper
import com.example.mysamplemovieapp.network.MainRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _moviesCollectionApi = MutableLiveData<ArrayList<MoviesDataClassItem>>()
    private var _moviesCollection = MutableLiveData<ArrayList<MoviesDataClassItem>>()
    val moviesCollection: LiveData<ArrayList<MoviesDataClassItem>?> = _moviesCollection
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    private val mainRepo = MainRepo()

    init {
        callMoviesCollectionApi()
    }


    private fun callMoviesCollectionApi() {
        viewModelScope.launch {
            try {
                val response = mainRepo.getMovieList()
                if (response.isSuccessful) {
                    if (response.body()!!.isNotEmpty()) {
                        _moviesCollectionApi.value = response.body()
                        _moviesCollection.value = response.body()
                    } else {
                        _errorMessage.value = "No Data Available"
                    }
                } else {
                    _errorMessage.value = "Something went wrong please try again later"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error please try again later"
            }
        }

    }


    fun search(searchedText: String) {
        val filterHelper = FilterHelper()
        val searchResult =
            _moviesCollectionApi.value?.let { filterHelper.search(searchedText = searchedText, it) }
        if (searchResult.isNullOrEmpty()) {
            _errorMessage.value = "No search results found!"
            _moviesCollection.value = searchResult
        } else {
            _moviesCollection.value = searchResult
        }
    }

}