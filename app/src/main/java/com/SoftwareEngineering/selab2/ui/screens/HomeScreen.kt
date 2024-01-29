package com.SoftwareEngineering.selab2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoftwareEngineering.selab2.R
import com.SoftwareEngineering.selab2.ui.App

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {

//        ButtonSection(name = "LAMP")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            var lightsOn by remember { mutableStateOf(true) }

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
                    text = if (lightsOn) "ON" else "OFF",
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
                    painter = if (lightsOn) painterResource(R.drawable.lighton)
                        else painterResource(R.drawable.lightoff),
                    contentDescription = "",
                )

                Switch(
                    modifier = Modifier
                        .semantics { contentDescription = "" },
                    checked = lightsOn,
                    onCheckedChange = { lightsOn = it }
                )


            }
        }



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
                text = "OPEN",
                fontSize = 20.sp
            )
        }

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
                text = "OPEN",
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun ButtonSection(
    name:String,

    ) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
//            fontSize = 20.sp
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}