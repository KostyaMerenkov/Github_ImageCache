package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomImage
import java.net.URL

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<RoomImage>)

    @Update
    fun update(image: RoomImage)

    @Update
    fun update(vararg images: RoomImage)

    @Update
    fun update(images: List<RoomImage>)

    @Delete
    fun delete(image: RoomImage)

    @Delete
    fun delete(vararg images: RoomImage)

    @Delete
    fun delete(images: List<RoomImage>)

    @Query("SELECT * FROM RoomImage")
    fun getAll(): List<RoomImage>

    @Query("SELECT * FROM RoomImage WHERE url = :userUrl LIMIT 1")
    fun findByUrl(userUrl: String): RoomImage?

}