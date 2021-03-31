package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUserImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomImage
import java.net.URL

interface IImageCache {
    fun putImage(url: String, byteArray: ByteArray): Completable
    fun getImage(url: String): Single<ByteArray>
}