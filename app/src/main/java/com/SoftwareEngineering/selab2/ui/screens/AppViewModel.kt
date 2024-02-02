package com.SoftwareEngineering.selab2.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.SoftwareEngineering.selab2.data.States
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface StatesUiStates {
    data class Success(val states:States): StatesUiStates
    object Error : StatesUiStates
    object Loading: StatesUiStates
}


class AppViewModel: ViewModel() {

    var statesUIStates: StatesUiStates by mutableStateOf(StatesUiStates.Loading)
        private set

    init {
        fetchStatesData()
    }

    private fun fetchStatesData() {
        statesUIStates = StatesUiStates.Loading

        FirebaseDatabase.getInstance().getReference("states")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val currStates = snapshot.getValue(States::class.java)
                    statesUIStates = currStates?.let { StatesUiStates.Success(it) }!!
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Oopsie", "Data fetching cancelled: ${error.message}")
                    statesUIStates = StatesUiStates.Error
                }
            })
    }

    fun updateDatabase(field: String, value: Boolean) {
        val statesReference = FirebaseDatabase.getInstance().getReference("states")
        statesReference.child(field).setValue(value)
    }
}