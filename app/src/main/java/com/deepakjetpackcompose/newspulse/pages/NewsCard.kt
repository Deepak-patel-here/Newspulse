package com.deepakjetpackcompose.newspulse.pages

import android.R.attr.shape
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.deepakjetpackcompose.newspulse.database.News
import com.deepakjetpackcompose.newspulse.news.model.Articles
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsCard(navController: NavController,news: News, modifier: Modifier = Modifier) {
    val encodedImg = URLEncoder.encode(news.imgUrl, StandardCharsets.UTF_8.toString())
        Card (modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)){
            NewsTemplate(news = news)
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)) {
                Text(text = formatTimeAgo(news.published),modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("readScreen/${encodedImg}/${news.title}/${news.description}")
                    })) {
                    Text("Read More", color = Color(0xFF471AA0))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF471AA0)
                    )
                }
            }
        }



}


@Composable
fun NewsTemplate(news: News,modifier: Modifier = Modifier) {
    var title=if(news.title.length>=47) "${news.title.substring(0,47)}..." else news.title
    var description = if(news.description.length>=60) {"${news.description.substring(0,60)}..."} else {news.description}
    var img=if(news.imgUrl!= "")news.imgUrl else R.drawable.news
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)){
        Row (){
            Image(
                painter = rememberAsyncImagePainter(img),
                contentDescription = null,
                modifier = Modifier.size(150.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )

            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,modifier= Modifier.padding(10.dp))
        }
        Text(text = description, modifier = Modifier.alpha(0.7f).padding(5.dp), fontSize = 15.sp)
    }
}



@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeAgo(timeString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")
    val zonedDateTime = ZonedDateTime.parse(timeString, formatter)

    val now = ZonedDateTime.now(ZoneOffset.UTC) // Current time in UTC
    val duration = Duration.between(zonedDateTime, now)

    return when {
        duration.toHours() > 0 -> "${duration.toHours()} hours ago"
        duration.toMinutes() > 0 -> "${duration.toMinutes()} minutes ago"
        else -> "Just now"
    }
}