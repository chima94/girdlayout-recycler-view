package com.example.roomdatabase.trackmysleepquality.sleepdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.sleepquality.SleepQualityViewModel
import javax.sql.CommonDataSource

class SleepDetailViewModelFactory(private val sleepNightKey : Long, private val dataSource: SleepDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)) {
            return SleepDetailViewModel(sleepNightKey, dataSource ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }
