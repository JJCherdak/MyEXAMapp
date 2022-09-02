package com.geekbrains.myexamapp.data

import com.geekbrains.myexamapp.domain.HomeWork
import com.geekbrains.myexamapp.domain.Class
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getClasses(): Single<List<Class>>
    fun getHomeWorks(): Single<List<HomeWork>>
}