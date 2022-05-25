package com.ubaya.adv160419162week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ubaya.adv160419162week8.R
import com.ubaya.adv160419162week8.databinding.FragmentCreateTodoBinding
import com.ubaya.adv160419162week8.model.Todo
import com.ubaya.adv160419162week8.util.NotificationHelper
import com.ubaya.adv160419162week8.util.TodoWorker
import com.ubaya.adv160419162week8.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [CreateTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTodoFragment : Fragment(),ButtonAddTodoClickListener,RadioButtonListener, TodoDateListener {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentCreateTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        dataBinding.todo = Todo("","",3,0)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        dataBinding.addListener = this
        dataBinding.radioListener = this
        dataBinding.dateListener = this
//        btnAdd.setOnClickListener {
//            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            var todo = Todo(editTitle.text.toString(), editNotes.text.toString(), radio.tag.toString().toInt())
//            var list = listOf(todo)
//            viewModel.addTodo(list)
//            Toast.makeText(view.context, "Data added",Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
////            NotificationHelper(view.context).createNotification("Todo created","A new todo has been created! Stay focus!")
//            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
//                .setInitialDelay(5, TimeUnit.SECONDS)
//                .setInputData(workDataOf(
//                    "title" to "Todo created",
//                    "message" to "A new todo has been created! stay focus"
//                )).build()
//            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
//        }

    }

    override fun onButtonAddTodo(view: View) {
        dataBinding.todo?.let {
            val list = listOf(it)
            viewModel.addTodo(list)
            Toast.makeText(view.context,"Todo created",Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).popBackStack()
            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setInputData(workDataOf(
                    "title" to "Todo ${it.title} created",
                    "message" to "A new todo has been created: ${it.note} stay focus"
                )).build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        }
    }

    override fun onRadioClick(view: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onDateClick(view: View) {
        TODO("Not yet implemented")
    }

    override fun onTimeClick(view: View) {
        TODO("Not yet implemented")
    }

}