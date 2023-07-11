package com.app.trello.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class, UserEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract  fun postdoa():PostsDao
    abstract  fun userdoa():UserDao
}