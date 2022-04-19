package com.ubaya.adv160419162week8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160419162week8.R
import com.ubaya.adv160419162week8.model.Todo
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class  TodoViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_todo_item, parent,false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.checkTask.setText(todoList[position].title)
    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}