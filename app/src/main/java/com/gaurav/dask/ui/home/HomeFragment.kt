package com.gaurav.dask.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.dask.NavigationDirections
import com.gaurav.dask.R
import com.gaurav.dask.core.ui.BaseFragment
import com.gaurav.dask.core.ui.utils.viewBinding
import com.gaurav.dask.custom.SimpleAdapter
import com.gaurav.dask.data.model.table.Task
import com.gaurav.dask.databinding.FragmentHomeBinding
import com.gaurav.dask.databinding.RvItemTaskBinding
import com.gaurav.dask.ui.task.TaskVM
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.flow.collect

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding<FragmentHomeBinding>()
    private val taskVM by viewModel<TaskVM>()
    private val taskAdapter =SimpleAdapter(list = ArrayList<Task>(),vh = {layoutInflater, viewGroup ->
        TaskVH(RvItemTaskBinding.inflate(layoutInflater,viewGroup,false))
    },viewBinder = {holder,item->
        holder.bind(item)
    })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding.rvTask){
            adapter=taskAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }
        binding.fabTask.setOnClickListener {
            navigate(NavigationDirections.toTask())
        }
        lifecycleScope.launch {
            taskVM.getAllTask().collect {
                taskAdapter.updateList(ArrayList(it))
            }
        }
    }


    inner class TaskVH(private val binding: RvItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.tvTask.text = item.task
        }
    }
}
