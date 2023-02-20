package com.example.mysamplemovieapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysamplemovieapp.components.MovieGrid
import com.example.mysamplemovieapp.components.SearchBar
import com.example.mysamplemovieapp.ui.theme.MyAppTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cachePath = cacheDir
        setContent {
            MyAppTheme {
                Scaffold {
                    MyApp(mainViewModel, it)
                }
            }

        }
        mainViewModel.apply {
            errorMessage.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        lateinit var cachePath: File
    }
}


@Composable
fun MyApp(`mainView-model`: MainViewModel, paddingValues: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(`mainView-model`, modifier = Modifier)
        MovieGrid(`mainView-model`, modifier = Modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//MyApp(MainViewModel(), PaddingValues(16.dp))
}

