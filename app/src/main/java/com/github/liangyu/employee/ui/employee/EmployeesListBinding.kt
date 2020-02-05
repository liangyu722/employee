package com.github.liangyu.employee.ui.employee

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.liangyu.employee.model.Employee

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Employee>) {
    (listView.adapter as EmployeesAdapter).submitList(items)
}

@BindingAdapter("app:imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}
