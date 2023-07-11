package com.app.trello.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_TABLE")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId :Int,
    @ColumnInfo(name = "token")
    val token:String,
    @ColumnInfo(name = "user_id")
    val user_id : Int,
    @ColumnInfo(name = "full_name")
    val full_name:String,
    @ColumnInfo(name = "avatar")
    val avatar:String
)