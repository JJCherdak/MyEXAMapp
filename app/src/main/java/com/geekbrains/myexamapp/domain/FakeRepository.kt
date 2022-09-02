package com.geekbrains.myexamapp.domain

import io.reactivex.rxjava3.core.Single

class FakeRepository {
    private val classes = listOf(
        Class(1,"Math", "08:00-08:45","Very long description of this class"),
        Class(2,"Math", "08:00-08:45","Very long description of this class"),
        Class(3,"Literature", "09:00-09:45","Very long description of this class"),
        Class(4,"Sport", "10:00-10:45","Very long description of this class"),
        Class(5,"Physics", "11:00-11:45","Very long description of this class"),
        Class(6,"Math", "12:00-12:45","Very long description of this class"),
        Class(7,"Literature", "13:00-13:45","Very long description of this class"),
        Class(8,"History", "14:00-14:45","Very long description of this class"),
        Class(9,"Math", "15:00-15:45","Very long description of this class"),
        Class(10,"Literature", "16:00-16:45","Very long description of this class"),
        Class(11,"Math", "17:00-17:45","Very long description of this class"),
        Class(12,"Literature", "18:00-18:45","Very long description of this class"))

    private val homework = listOf(
        HomeWork("Physics", "1 day left","Very long description of this class"),
        HomeWork("Math", "3 day left","Very long description of this class"),
        HomeWork("Literature", "4 day left","Very long description of this class"),
        HomeWork("Sport", "5 day left","Very long description of this class"),
        HomeWork("Physics", "7 day left","Very long description of this class"),
        HomeWork("Math", "8 day left","Very long description of this class"),
        HomeWork("Literature", "9 day left","Very long description of this class"),
        HomeWork("History", "11 day left","Very long description of this class"))

    fun getClasses(): Single<List<Class>> = Single.just(classes)
    fun getHomeworks(): Single<List<HomeWork>> = Single.just(homework)
}