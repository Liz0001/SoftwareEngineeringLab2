package com.SoftwareEngineering.selab2.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SoftwareEngineering.selab2.VoiceToText
import com.SoftwareEngineering.selab2.data.VoiceToTextState
import com.SoftwareEngineering.selab2.ui.screens.AppViewModel
import com.SoftwareEngineering.selab2.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(state: VoiceToTextState, voiceToText: VoiceToText) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            val statesViewModel: AppViewModel = viewModel()
            HomeScreen(vm = statesViewModel, state = state, voiceToText, statesUIStates = statesViewModel.statesUIStates)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                text = "Smart House",
            )
        },
        modifier = modifier,
    )
}
