package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database

class UsersCache(private val db: Database): IUsersCache {
    override fun putUsers(users: List<GithubUser>) = Completable.fromAction {
            val roomUsers = users.map { user ->
                RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
            }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }.subscribeOn(Schedulers.io())
}