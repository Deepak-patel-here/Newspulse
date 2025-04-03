package com.deepakjetpackcompose.newspulse.pages

import android.util.MutableBoolean
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deepakjetpackcompose.newspulse.R


@Composable
fun BottomAppBar(selected: MutableState<Int>, modifier: Modifier = Modifier) {

    Card (modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
        colors = CardDefaults.cardColors(Color(0xFF471AA0)),
//        elevation = CardDefaults.cardElevation(),
        shape = RectangleShape
    ){

        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween){

            BottomItems(
                if (selected.value==0){Color.Yellow} else {Color.White},
                onClick = {
                    selected.value=0
                          },
                R.drawable.home_h
            )
            BottomItems(
                if (selected.value==1){Color.Yellow} else {Color.White},
                onClick = {selected.value=1},
                R.drawable.technology
            )
            BottomItems(
                if (selected.value==2){Color.Yellow} else {Color.White},
                onClick = {selected.value=2},
                R.drawable.volleyball
            )
            BottomItems(
                if (selected.value==3){Color.Yellow} else {Color.White},
                onClick = {selected.value=3},
                R.drawable.flask
            )

            
        }

    }

}


@Composable
fun BottomItems(color:Color, onClick:()-> Unit, img:Int,modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(3.dp).clickable(onClick = {onClick()})) {
        Image(
            painter = painterResource(img),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = color)
        )
    }
    
}