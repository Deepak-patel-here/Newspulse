package com.deepakjetpackcompose.newspulse.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.newspulse.R


@Composable
fun CardSignIn(img:Int, onClick:()-> Unit,modifier: Modifier = Modifier) {
    Card (modifier = modifier
        .background(MaterialTheme.colorScheme.background)
        .size(40.dp)
        .clickable(onClick = {onClick()}
        ),
        elevation = CardDefaults.elevatedCardElevation(5.dp)){
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(img),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(30.dp)
                    .padding(3.dp)

            )
        }

    }

}