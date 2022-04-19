package com.ubaya.adv160419162week8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.ubaya.adv160419162week8.model.Todo
import com.ubaya.adv160419162week8.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    private val job = Job()

    fun addTodo(todoList: List<Todo>){
        launch {
            val db = Room.databaseBuilder(getApplication(),TodoDatabase::class.java,
            "newtododb").build()
            db.todoDao().insertAll(*todoList.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}