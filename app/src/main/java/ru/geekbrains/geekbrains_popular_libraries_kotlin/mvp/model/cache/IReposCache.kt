package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser

interface IReposCache {
    fun putRepos(repos: List<GithubRepository>, user: GithubUser): Completable
    fun getRepos(user: GithubUser): Single<List<GithubRepository>>
}