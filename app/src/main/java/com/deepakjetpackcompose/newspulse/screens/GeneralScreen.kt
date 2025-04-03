package com.deepakjetpackcompose.newspulse.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.newspulse.news.model.Articles
import com.deepakjetpackcompose.newspulse.news.model.NewsResponse
import com.deepakjetpackcompose.newspulse.pages.NewsCard
import com.deepakjetpackcompose.newspulse.viewmodel.NewsViewModel
import io.ktor.websocket.Frame.Text
import  androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralScreen(navController: NavController,newsViewModel: NewsViewModel, modifier: Modifier = Modifier) {
    var generalData by remember { mutableStateOf<NewsResponse?>(null) }
    val articleList by newsViewModel.news.observeAsState(emptyList())
    val isLoading by newsViewModel.isLoading.observeAsState(false)


    Column(modifier=modifier) {
        Row {
            Text(
                text = "All News ",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF471AA0),
                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.clickable { newsViewModel.fetchAllNews() }.padding(end = 10.dp)
            )
        }

        if (isLoading) {
            Text("Fetching latest news...", fontSize = 16.sp, modifier = Modifier.padding(16.dp))
        } else if (articleList.isEmpty()) {
            Text(
                "No latest news ,try refreshing",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        } else {
                LazyColumn {
                    items(articleList) { item ->
                        NewsCard(navController = navController, item)
                    }
                }
            }
        }
    }
