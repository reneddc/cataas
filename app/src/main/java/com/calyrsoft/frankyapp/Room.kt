package com.calyrsoft.frankyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Observer

import com.calyr.data.Cat
import com.calyrsoft.frankyapp.ui.theme.AppTheme

class CatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingCats(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingCats(modifier: Modifier = Modifier) {
    var listOfCats by remember { mutableStateOf(listOf<Cat>()) }
    val context = LocalContext.current
    val catViewModel = CatViewModel()

    catViewModel.loadCats(context)
    val lifecycleOwner = LocalLifecycleOwner.current

    fun updateUi(cats: List<Cat>) {
        listOfCats = cats
    }

    catViewModel.cats.observe(
        lifecycleOwner,
        Observer(::updateUi)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        items(listOfCats.size) { index ->
            Text(
                text = "Cat ID: ${listOfCats[index].id}, MimeType: ${listOfCats[index].mimeType}, Size: ${listOfCats[index].size}, Tags: ${listOfCats[index].tags.joinToString()}"
            )
        }
    }
}
