package com.example.mysamplemovieapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysamplemovieapp.MainViewModel
import com.example.mysamplemovieapp.R


@Composable
fun SearchBar(`mainView-model`: MainViewModel, modifier: Modifier) {
    val focusManager = LocalFocusManager.current
    val textState = rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = textState.value,
        onValueChange = { value ->
            textState.value = value
            `mainView-model`.search(textState.value)
        },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder_search)
            )
        },
        colors = TextFieldDefaults.textFieldColors(MaterialTheme.colors.onPrimary),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .heightIn(min = 48.dp)
            .fillMaxWidth()
            .background(Color.LightGray),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }),
        singleLine = true
    )

}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchBar(MainViewModel(),Modifier)
}
