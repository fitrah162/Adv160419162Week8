package com.ubaya.adv160419162week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.adv160419162week8.R
import com.ubaya.adv160419162week8.model.Todo
import com.ubaya.adv160419162week8.viewmodel.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment() {

    private lateinit var viewModel:TodoListViewModel
    private var todoListAdapter = TodoListAdapter(arrayListOf()){
        viewModel.update(it.uuid)
        Toast.makeText(view?.context, "Todo done",Toast.LENGTH_SHORT).show()
        observeViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        viewModel.refresh()
        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = todoListAdapter

        fabAddTodo.setOnClickListener{
            val action = TodoListFragmentDirections.actionTodoListFragmentToCreateTodoFragment()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            if(it.isEmpty()){
                txtEmpty.visibility = View.VISIBLE
            }else{
                txtEmpty.visibility = View.GONE
            }
        })
    }

}