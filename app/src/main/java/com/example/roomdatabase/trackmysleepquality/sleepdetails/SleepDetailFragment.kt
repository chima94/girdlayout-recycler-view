package com.example.roomdatabase.trackmysleepquality.sleepdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.sleepquality.SleepQualityFragmentDirections
import com.example.android.trackmysleepquality.sleepquality.SleepQualityViewModelFactory
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentSleepDetailBinding


class SleepDetailFragment : Fragment() {
    private lateinit var binding : FragmentSleepDetailBinding
    private lateinit var viewModel : SleepDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(arguments)
        Toast.makeText(activity, "${arguments.nightId}",Toast.LENGTH_LONG).show()
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.nightId, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepDetailViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_detail, container, false)

        binding.sleepDetails = viewModel
        binding.setLifecycleOwner(this)
       viewModel.getNight().observe(viewLifecycleOwner, Observer {  })

        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {

           if(it == true){
               findNavController().navigate(SleepDetailFragmentDirections.actionSleepDetailToSleepTrackerFragment())
               viewModel.onNavigated()
           }


        })
        return binding.root
    }


}