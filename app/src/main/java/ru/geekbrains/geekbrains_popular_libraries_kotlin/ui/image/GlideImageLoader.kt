package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.image.IImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class GlideImageLoader(
    private val networkStatus: INetworkStatus,
    private val cache: IImageCache
) : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    //Обработка провала загрузки
                    return false
                }

                override fun onResourceReady(
                    bitmap: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    //Делаем что-то с bitmap
                    networkStatus.isOnlineSingle().map { isOnline ->
                        if(isOnline){
                            bitmap?.let { bitmap ->
                                cache.putImage(url, bitmap.convertToByteArray()) //.toSingleDefault(bitmap)
                            } //?: Single.error (RuntimeException("User has no repos url"))
                        } else {
                            cache.getImage(url).map {
                                BitmapFactory.decodeByteArray(it, 0, it.size)
                            }
                        }
                    }.subscribeOn(Schedulers.io())
                    return false
                }
            })
            .into(container)
    }

    fun Bitmap.convertToByteArray(): ByteArray = ByteBuffer.allocate(byteCount).apply {
        copyPixelsToBuffer(this)
        rewind()
    }.array()

}