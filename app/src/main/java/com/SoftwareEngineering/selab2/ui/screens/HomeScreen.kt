package com.SoftwareEngineering.selab2.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoftwareEngineering.selab2.R
import com.SoftwareEngineering.selab2.data.States

@Composable
fun HomeScreen(
    statesViewModel: MutableState<States?>,
    modifier: Modifier = Modifier
) {
    Log.i("HomeScreen", "doorOpen: ${statesViewModel.value?.doorOpen}, lightOn: ${statesViewModel.value?.lightOn}, windowOpen: ${statesViewModel.value?.windowOpen}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        // LAMP
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            var lightsOn by remember { mutableStateOf(statesViewModel.value?.lightOn) }
            Log.i("HomeScreen", "lightOn: ${lightsOn} ..............................................")

            Row(
                modifier = Modifier
                    .padding(vertical = 30.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "LAMP: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (lightsOn == true) "ON" else "OFF",
                    fontSize = 20.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {

                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp),
                    painter = if (lightsOn == true) painterResource(R.drawable.lighton)
                    else painterResource(R.drawable.lightoff),
                    contentDescription = "",
                    colorFilter = if (lightsOn == true) ColorFilter.tint(color = Color.Black)
                    else ColorFilter.tint(color = Color.LightGray)
                )

                Switch(
                    modifier = Modifier
                        .semantics { contentDescription = "" },
                    checked = lightsOn == true,
                    onCheckedChange = { lightsOn = it }
                )

            }
        }

        // DOOR
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            var doorOpen by remember { mutableStateOf(statesViewModel.value?.doorOpen) }
            Log.i("HomeScreen", "doorOpen: ${doorOpen}..............................................")


            Row(
                modifier = Modifier
                    .padding(vertical = 30.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "DOOR: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (doorOpen == true) "OPEN" else "CLOSED",
                    fontSize = 20.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {

                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp),
                    painter = if (doorOpen == true) painterResource(R.drawable.dooropen)
                    else painterResource(R.drawable.doorclosed),
                    contentDescription = "",
                    colorFilter = if (doorOpen == true) ColorFilter.tint(color = Color.Black)
                    else ColorFilter.tint(color = Color.LightGray)
                )

                Switch(
                    modifier = Modifier
                        .semantics { contentDescription = "" },
                    checked = doorOpen == true,
                    onCheckedChange = { doorOpen = it }
                )
            }
        }

        // WINDOW
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            var windowOpen by remember { mutableStateOf(statesViewModel.value?.windowOpen) }
            Log.i("HomeScreen", "windowOpen: ${windowOpen} ..............................................")

            Row(
                modifier = Modifier
                    .padding(vertical = 30.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "WINDOW: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (windowOpen == true) "OPEN" else "CLOSED",
                    fontSize = 20.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {

                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp),
                    painter = if (windowOpen == true) painterResource(R.drawable.windowopen)
                    else painterResource(R.drawable.windowclosed),
                    contentDescription = "",
                    colorFilter = if (windowOpen == true) ColorFilter.tint(color = Color.Black)
                    else ColorFilter.tint(color = Color.LightGray)
                )

                Switch(
                    modifier = Modifier
                        .semantics { contentDescription = "" },
                    checked = windowOpen == true,
                    onCheckedChange = { windowOpen = it }
                )
            }
        }
    }
}
