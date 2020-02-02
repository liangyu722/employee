package com.github.liangyu.employee.ui.employee

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.liangyu.employee.model.Employee

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Employee>) {
    (listView.adapter as EmployeesAdapter).submitList(items)
}
