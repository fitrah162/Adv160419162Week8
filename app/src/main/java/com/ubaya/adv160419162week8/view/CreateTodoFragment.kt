package com.ubaya.adv160419162week8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.adv160419162week8.R


/**
 * A simple [Fragment] subclass.
 * Use the [CreateTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTodoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

}