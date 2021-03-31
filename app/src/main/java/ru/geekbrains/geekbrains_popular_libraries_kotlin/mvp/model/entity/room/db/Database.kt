package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.ImageDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.UserDao


@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class,
        RoomImage::class
    ],
    version = 2
)
abstract class Database : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

//    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE  ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL")
//        }
//    }

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")
        fun create(context: Context) {
            if (instance == null) {
                //instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
