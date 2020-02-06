package com.github.liangyu.employee.ui.employee

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.liangyu.employee.R
import com.github.liangyu.employee.common.setupToast
import com.github.liangyu.employee.databinding.EmployeesActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class EmployeesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ViewEmployeesViewModel
    private lateinit var listAdapter: EmployeesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employees_activity)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EmployeesViewModel::class.java)
        val binding = DataBindingUtil.setContentView<EmployeesActivityBinding>(
            this,
            R.layout.employees_activity
        )
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.root.setupToast(this, viewModel.errorMessage)
        listAdapter = EmployeesAdapter()
        binding.employeeList.adapter = listAdapter
    }
}
