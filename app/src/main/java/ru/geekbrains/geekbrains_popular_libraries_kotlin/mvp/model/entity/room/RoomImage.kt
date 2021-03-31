package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import retrofit2.http.Url
import java.net.URL

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["avatarUrl"],
        childColumns = ["url"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomImage(
    @PrimaryKey val url: String,
    val byteArray: ByteArray
)