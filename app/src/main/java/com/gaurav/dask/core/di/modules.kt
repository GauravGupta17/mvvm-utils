package com.gaurav.dask.core.di

import com.gaurav.dask.data.offline.db.AppDatabase
import com.gaurav.dask.ui.task.TaskRepo
import com.gaurav.dask.ui.task.TaskVM
import org.koin.dsl.module


val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
}
val repoModule= module {
    single { TaskRepo(get()) }
}
val vmModule = module {
    single { TaskVM(get()) }
}

