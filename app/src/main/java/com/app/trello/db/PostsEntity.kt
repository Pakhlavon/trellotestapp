package com.app.trello.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "POSTS_TABLE")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId :Int,

    @ColumnInfo("task_id")
    val task_id: Int,

    @ColumnInfo("index")
    val index: Int,

    @ColumnInfo("project_id")
    val project_id: String,

    @ColumnInfo("project_name")
    val project_name: String,

    @ColumnInfo("owner_id")
    val owner_id: Int,

    @ColumnInfo("owner_name")
    val owner_name: String,

    @ColumnInfo("owner_avatar")
    val owner_avatar: String,

    @ColumnInfo("executor_id")
    val executor_id: Int,

    @ColumnInfo("executor_name")
    val executor_name: String,

    @ColumnInfo("executor_avatar")
    val executor_avatar: String,

    @ColumnInfo("task_date")
    val task_date: String,

    @ColumnInfo("term_date")
    val term_date: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("priority")
    val priority: String,

    @ColumnInfo("status")
    val status: String

)