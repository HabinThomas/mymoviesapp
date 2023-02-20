package com.example.mysamplemovieapp.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mysamplemovieapp.R
import com.example.mysamplemovieapp.MainViewModel
import com.example.mysamplemovieapp.dataClass.MoviesDataClassItem


@Composable
fun MovieGrid(
    `mainView-model`: MainViewModel,
    modifier: Modifier = Modifier
) {
    val items by `mainView-model`.moviesCollection.observeAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items?.let {
            items(it.toList()) { item ->
                MovieCard(item)
            }
        }

    }
}

@Composable
fun MovieCard(item: MoviesDataClassItem) {
    Surface(shape = MaterialTheme.shapes.small) {
        Box(Modifier.size(195.dp, 200.dp)) {
            AsyncImage(
                model = item.Poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.img_placeholder),
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridPreview() {
    MovieGrid(MainViewModel())
}