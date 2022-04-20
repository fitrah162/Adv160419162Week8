package com.ubaya.adv160419162week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.adv160419162week8.R
import com.ubaya.adv160419162week8.model.Todo
import com.ubaya.adv160419162week8.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*


/**
 * A simple [Fragment] subclass.
 * Use the [CreateTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTodoFragment : Fragment() {

    private lateinit var viewModel: DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        btnAdd.setOnClickListener {
            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            var todo = Todo(editTitle.text.toString(), editNotes.text.toString(), radio.tag.toString().toInt())
            var list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added",Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

}