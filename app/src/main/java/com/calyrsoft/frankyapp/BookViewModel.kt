package com.calyrsoft.frankyapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calyr.data.Book
import com.calyr.data.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel:ViewModel() {
    val books : LiveData<List<Book>>
            get() = _books

    private val _books = MutableLiveData<List<Book>>()

    fun  loadBook(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = BookRepository(context)
            repository.insert(Book("the best seller: Android"))
            val lista = repository.getListBooks()
            lista.forEach {
                Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}")
            }
            withContext(Dispatchers.Main) {
                _books.value = lista
            }
        }
    }
}