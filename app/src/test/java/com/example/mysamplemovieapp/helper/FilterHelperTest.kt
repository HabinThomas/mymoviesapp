package com.example.mysamplemovieapp.helper

import com.example.mysamplemovieapp.dataClass.MoviesDataClassItem
import org.junit.Assert.*
import org.junit.Test

class FilterHelperTest {

    @Test
    fun searchFilterTest() {
        val testList = ArrayList<MoviesDataClassItem>()
        testList.apply {
            add(
                MoviesDataClassItem(
                    Genre = "Documentary, History, War",
                    Title = "They Shall Not Grow Old",
                    Poster = ""
                )
            )
            add(
                MoviesDataClassItem(
                    Genre = "Action, Comedy, Crime, Drama",
                    Title = "Pain & Gain",
                    Poster = ""
                )
            )
            add(
                MoviesDataClassItem(
                    Genre = "Biography, Crime, Drama",
                    Title = "The Irishman",
                    Poster = ""
                )
            )
            add(
                MoviesDataClassItem(
                    Genre = "Comedy, Drama, Thriller",
                    Title = "Parasite",
                    Poster = ""
                )
            )
            add(
                MoviesDataClassItem(
                    Genre = "Comedy, Crime, Drama, Mystery, Thriller",
                    Title = "Knives Out",
                    Poster = ""
                )
            )
        }
        val filterHelperTest = FilterHelper()
        val result = filterHelperTest.search("Pain & Gain", testList)
        assertEquals("Pain & Gain", result[0].Title)
        val result2 = filterHelperTest.search("War", testList)
        assertEquals(1, result2.size)
        val result3 = filterHelperTest.search("Mark", testList)
        assertEquals(0, result3.size)
    }
}