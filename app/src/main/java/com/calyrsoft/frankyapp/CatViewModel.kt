package com.calyrsoft.frankyapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calyr.data.Cat
import com.calyr.data.CatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatViewModel: ViewModel() {

    val cats: LiveData<List<Cat>>
        get() = _cats

    private val _cats = MutableLiveData<List<Cat>>()

    fun loadCats(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = CatRepository(context)

            repository.insert(Cat("example_id", "image/jpeg", 12345, listOf("Cute", "Grey")))

            val lista = repository.getListCats()

            lista.forEach {
                Log.d("DBTEST", "Id cat = ${it.id}, MimeType: ${it.mimetype}, Size: ${it.size}, Tags: ${it.tags}")
            }

            withContext(Dispatchers.Main) {
                _cats.value = lista
            }
        }
    }
}
