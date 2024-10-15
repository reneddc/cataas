package com.calyrsoft.frankyapp

import android.annotation.SuppressLint
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

import com.calyr.data.Book
import com.calyrsoft.frankyapp.ui.theme.AppTheme


class BookActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    var listOfBooks by remember { mutableStateOf( listOf<Book>())}
    val context = LocalContext.current
    val bookViewModel = BookViewModel()
    bookViewModel.loadBook(context)
    val lifecycleOwner = LocalLifecycleOwner.current
    fun updateUi(books: List<Book>) {
        listOfBooks = books
    }
    bookViewModel.books.observe(
        lifecycleOwner,
        Observer(::updateUi)
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        items( listOfBooks.size) {
            Text(
                text = "Book ${listOfBooks[it].title}"
            )
        }
    }
}
