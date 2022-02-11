package com.gaurav.dask.ui.task

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.lifecycleScope
import com.gaurav.dask.R
import com.gaurav.dask.core.ui.BaseFragment
import com.gaurav.dask.core.ui.utils.viewBinding
import com.gaurav.dask.data.model.table.Task
import com.gaurav.dask.databinding.FragmentNewTaskBinding
import com.gaurav.dask.utils.extensions.watchInputFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.views.InputType.Companion.date
import java.text.SimpleDateFormat
import java.util.*

class NewTaskFragment : BaseFragment(R.layout.fragment_new_task) {
    private val binding by viewBinding<FragmentNewTaskBinding>()
    private val taskVM by viewModel<TaskVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          initViews()
    }

    private fun initViews() {
        binding.etTask.requestFocus()
        binding.btnSave.isEnabled=!binding.etTask.text.isNullOrEmpty()
        watchInputFields(binding.etTask){
            binding.btnSave.isEnabled=!binding.etTask.text.isNullOrEmpty()
        }

        binding.btnSave.setOnClickListener {
            val dob = if (binding.etDob.text.isNullOrEmpty()) null else binding.etDob.text.toString()
            lifecycleScope.launch {
                taskVM.addTask(Task(task = binding.etTask.text.toString(),date =dob))
                backToPrevious()
            }
        }
        binding.etDob.setOnClickListener {
            selectDate()
        }
    }

    private fun selectDate(){
        val myCalendar = Calendar.getInstance();
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd-mm-yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                binding.etDob.setText(sdf.format(myCalendar.time))
                            }

            DatePickerDialog(
                requireContext(),R.style.ThemeOverlay_MaterialComponents_Dialog, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                this.datePicker.maxDate=System.currentTimeMillis()-86400000
                show()
            }

    }

}