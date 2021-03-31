package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
    @PrimaryKey val id: String,
    val login: String,
    //@Index(unique = true)
    val avatarUrl: String,
    val reposUrl: String? = null
)