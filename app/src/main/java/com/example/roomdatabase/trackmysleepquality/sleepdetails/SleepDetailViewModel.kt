package com.example.roomdatabase.trackmysleepquality.sleepdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.Job

class SleepDetailViewModel(private val nightId : Long = 0L, databaseDao: SleepDatabaseDao) : ViewModel() {

    private val night : LiveData<SleepNight>
    var database = databaseDao

    private val viewModelJob = Job()
    private var _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker : LiveData<Boolean?>
        get() = _navigateToSleepTracker

    init {
        night = database.getNightWithId(nightId)
    }

    fun getNight() = night

    fun onClose(){
        _navigateToSleepTracker.value = true
    }

    fun onNavigated(){
        _navigateToSleepTracker.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}