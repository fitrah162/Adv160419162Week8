package com.ubaya.adv160419162week8.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)

    @Query("SELECT * FROM todo")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo where uuid= :id")
    suspend fun selectTodo(id: Int):Todo

    @Delete
    suspend fun deleteTodo(todo:Todo)
}