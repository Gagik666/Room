package com.example.roomsqlite.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "realname") val realname: String?,
    @ColumnInfo(name = "team") val team: String?,
    @ColumnInfo(name = "imageurl") val imageurl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
