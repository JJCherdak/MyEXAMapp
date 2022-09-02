package com.geekbrains.myexamapp.data

import com.geekbrains.myexamapp.domain.FakeRepository
import com.geekbrains.myexamapp.domain.HomeWork
import io.reactivex.rxjava3.core.Single
import com.geekbrains.myexamapp.domain.Class

class RepositoryFake(private val repo: FakeRepository = FakeRepository()) : Repository {

    override fun getClasses(): Single<List<Class>> {
        return repo.getClasses()
    }

    override fun getHomeWorks(): Single<List<HomeWork>> {
        return repo.getHomeworks()
    }
}