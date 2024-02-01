package com.SoftwareEngineering.selab2.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.SoftwareEngineering.selab2.data.States
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AppViewModel: ViewModel() {

    private val _states = mutableStateOf<States?>(null)
    val states: MutableState<States?> = _states

    init {
        fetchStatesData()
    }

    private fun fetchStatesData() {

        FirebaseDatabase.getInstance().getReference("states")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val currStates = snapshot.getValue(States::class.java)
                    _states.value = currStates
//                    updateStates(currStates)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Oopsie", "Data fetching cancelled: ${error.message}")
                }
            })
    }

//    private fun updateStates(statesList: States?) {
//        // update the StateFlow with entire list...
//        runBlocking {
//            launch {
//                _states.add(statesList)
//            }
//        }
//    }
}