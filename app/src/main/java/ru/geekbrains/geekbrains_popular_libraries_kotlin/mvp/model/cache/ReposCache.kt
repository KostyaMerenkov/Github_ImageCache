package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import android.transition.Scene
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import java.lang.RuntimeException

class ReposCache(private val db: Database) : IReposCache {
    override fun putRepos(repos: List<GithubRepository>, user: GithubUser) = Completable.fromAction {
        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException("No user in DB")
        val roomRepos = repos.map { repo ->
            RoomGithubRepository(repo.id, repo.name, repo.forksCount, roomUser.id)
        }
        db.repositoryDao.insert(roomRepos)
    }.subscribeOn(Schedulers.io())

    override fun getRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException("No user in DB")
        db.repositoryDao.findForUser(roomUser.id).map { roomRepo ->
            GithubRepository(roomRepo.id, roomRepo.name, roomRepo.forksCount)
        }
    }.subscribeOn(Schedulers.io())
}