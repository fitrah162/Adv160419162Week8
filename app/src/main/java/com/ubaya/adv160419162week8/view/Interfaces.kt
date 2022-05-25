package com.ubaya.adv160419162week8.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.adv160419162week8.model.Todo

interface TodoCheckedChangeListener{
    fun onCheckedChange(cb:CompoundButton, isChecked:Boolean, obj: Todo)
}

interface TodoEditClickListener{
    fun onTodoEditClick(view:View)
}

interface RadioButtonListener{
    fun onRadioClick(view: View, priority: Int, obj: Todo)
}

interface TodoSaveChangeListener{
    fun onSaveChangeClick(view: View, obj: Todo)
}
interface ButtonAddTodoClickListener{
    fun onButtonAddTodo(view: View)
}
interface TodoDateListener{
    fun onDateClick(view: View)
    fun onTimeClick(view: View)
}