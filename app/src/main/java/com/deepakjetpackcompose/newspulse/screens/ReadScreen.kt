package com.deepakjetpackcompose.newspulse.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.deepakjetpackcompose.newspulse.R
import com.deepakjetpackcompose.newspulse.news.model.Articles
import com.deepakjetpackcompose.newspulse.pages.BackBtn
import com.deepakjetpackcompose.newspulse.pages.NewsCard
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun ReadScreen(navController: NavController,title:String,description:String,img:String,modifier: Modifier = Modifier) {
    val decodedImg = URLDecoder.decode(img, StandardCharsets.UTF_8.toString())
    val img=if(decodedImg==""){
        R.drawable.news
    }else {decodedImg}
        Column (modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(10.dp)){
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.clickable(
                    onClick = {navController.popBackStack()}
                ).padding(
                    16.dp
                )
            )
            {
                Icon(
                    painter = painterResource(R.drawable.left_arrow_two),
                    contentDescription = null,
                    tint = Color(0xFF471AA0),
                    modifier = Modifier.size(17.dp)
                )
                Text("Back", color = Color(0xFF471AA0), fontSize = 15.sp)
            }

            Column(modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {


                Box(modifier= Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)){
                    Image(
                        painter = rememberAsyncImagePainter(img),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(Modifier.height(20.dp))

                Box(){
                    Text(title, fontSize = 23.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(10.dp))


                Box(){
                    Text(description, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }


            }
        }


}


