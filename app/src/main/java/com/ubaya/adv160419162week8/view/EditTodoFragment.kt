package com.ubaya.adv160419162week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.adv160419162week8.R
import com.ubaya.adv160419162week8.databinding.FragmentEditTodoBinding
import com.ubaya.adv160419162week8.model.Todo
import com.ubaya.adv160419162week8.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*


class EditTodoFragment : Fragment(), RadioButtonListener, TodoSaveChangeListener  {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
        dataBinding.radioListener = this
        dataBinding.saveListener = this
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
//            editTitle.setText(it.title)
//            editNotes.setText(it.note)
//            when(it.priority){
//                1 -> radioLow.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioHigh.isChecked = true
//            }

        })

    }

    override fun onRadioClick(view: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onSaveChangeClick(view: View, obj: Todo) {
        viewModel.update(obj)
        Toast.makeText(view.context, "Todo updated",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }


}