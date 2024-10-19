package com.calyr.data

import android.content.Context

class CatRepository(val context: Context) {

    private val catDao = AppRoomDatabase.getDatabase(context).catDao()

    suspend fun insert(cat: Cat) {
        catDao.insert(cat)
    }

    fun getListCats(): List<Cat> {
        return catDao.getList()
    }

    suspend fun insertAll(cats: List<Cat>) {
        catDao.insertAll(cats)
    }
}
