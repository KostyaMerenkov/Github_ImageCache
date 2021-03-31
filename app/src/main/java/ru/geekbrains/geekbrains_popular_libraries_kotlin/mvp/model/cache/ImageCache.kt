package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import android.graphics.Bitmap
import androidx.room.Room
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUserImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import java.lang.RuntimeException
import java.net.URL

class ImageCache(val db: Database): IImageCache {

    override fun putImage(url: String, byteArray: ByteArray) = Completable.fromAction {
        db.imageDao.insert(RoomImage(url, byteArray))
    }.observeOn(Schedulers.io())

    override fun getImage(url: String) = Single.fromCallable {
        db.imageDao.findByUrl(url)?.let {
            it.byteArray
        } //?: Single.error(RuntimeException("we"))
    }.observeOn(Schedulers.io())
}