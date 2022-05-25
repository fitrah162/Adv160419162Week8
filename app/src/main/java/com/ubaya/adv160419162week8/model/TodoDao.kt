package com.ubaya.adv160419162week8.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)



    @Query("SELECT * FROM todo where uuid= :id")
    suspend fun selectTodo(id: Int):Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    suspend fun update(title:String, notes:String, priority: Int, id: Int)

    @Query("SELECT * FROM todo WHERE is_done =0 ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("UPDATE todo set is_done=1 WHERE uuid= :id")
    suspend fun update(id: Int)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo:Todo)
}