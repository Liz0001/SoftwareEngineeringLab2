package com.SoftwareEngineering.selab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.SoftwareEngineering.selab2.ui.App
import com.SoftwareEngineering.selab2.ui.theme.SELab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SELab2Theme {
                App()
            }
        }
    }
}
