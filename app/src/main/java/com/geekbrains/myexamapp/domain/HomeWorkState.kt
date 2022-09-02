package com.geekbrains.myexamapp.domain

sealed class HomeworkState {
    data class Success(val data: List<HomeWork>) : HomeworkState()
    class Error(val error: Throwable) : HomeworkState()
    object Loading : HomeworkState()
}