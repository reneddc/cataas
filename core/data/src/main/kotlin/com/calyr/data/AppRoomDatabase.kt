//package com.calyr.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [Book::class], version = 1)
//abstract class AppRoomDatabase : RoomDatabase() {
//    abstract fun bookDao(): IBookDao
//
//    companion object {
//        @Volatile
//        private var Instance: AppRoomDatabase? = null
//
//        fun getDatabase(context: Context): AppRoomDatabase {
//            // if the Instance is not null, return it, otherwise create a new database instance.
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, AppRoomDatabase::class.java, "item_database")
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
//}
package com.calyr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cat::class], version = 2)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun catDao(): ICatDao

    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppRoomDatabase::class.java, "cat_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

