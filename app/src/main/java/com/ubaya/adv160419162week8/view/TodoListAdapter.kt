package com.ubaya.adv160419162week8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160419162week8.R
import com.ubaya.adv160419162week8.databinding.LayoutTodoItemBinding
import com.ubaya.adv160419162week8.model.Todo
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Todo)-> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClickListener{
    class  TodoViewHolder(var view:LayoutTodoItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<LayoutTodoItemBinding>(inflater,R.layout.layout_todo_item,parent,false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.view.todo = todoList[position]
        holder.view.checkboxListener = this
        holder.view.editListener = this
//        holder.view.checkTask.setText(todoList[position].title)
//        holder.view.imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionTodoListFragmentToEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked){
//                adapterOnClick(todoList[position])
//            }
//        }
    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
       if(isChecked){
           adapterOnClick(obj)
       }
    }

    override fun onTodoEditClick(view: View) {
        val uuid = view.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionTodoListFragmentToEditTodoFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}