package com.calyr.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_table")
data class Cat(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,

    @ColumnInfo(name = "mimetype") val mimetype: String,

    @ColumnInfo(name = "size") val size: Int,

    @ColumnInfo(name = "tags") val tags: List<String>
)
