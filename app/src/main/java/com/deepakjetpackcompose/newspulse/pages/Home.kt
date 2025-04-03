package com.deepakjetpackcompose.newspulse.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deepakjetpackcompose.newspulse.viewmodel.AuthState
import com.deepakjetpackcompose.newspulse.viewmodel.AuthViewModel
import com.deepakjetpackcompose.newspulse.R
import com.deepakjetpackcompose.newspulse.screens.GeneralScreen
import com.deepakjetpackcompose.newspulse.screens.ScienceScreen
import com.deepakjetpackcompose.newspulse.screens.Sports
import com.deepakjetpackcompose.newspulse.screens.TechScreen
import com.deepakjetpackcompose.newspulse.ui.theme.NewspulseTheme
import com.deepakjetpackcompose.newspulse.viewmodel.NewsViewModel
import com.deepakjetpackcompose.newspulse.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,authViewModel: AuthViewModel,newsViewModel: NewsViewModel,themeViewModel: ThemeViewModel,modifier: Modifier = Modifier) {
    val authState = authViewModel.authState.observeAsState()
    val scope= rememberCoroutineScope()
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedState=remember { mutableStateOf(0) }
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated ->navController.navigate("login"){
                popUpTo("home"){inclusive=true}
            }
            else ->Unit
        }
    }

    NewspulseTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier.width(280.dp).fillMaxHeight()
                ){
                    Column {
                        DrawerHeader()
                        DrawerBody(onClick = {
                            authViewModel.signOut()
                        }, themeViewModel = themeViewModel)
                    }
                }
            }
        ) {
            Scaffold(
                modifier= modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
                },
                bottomBar = {
                    BottomAppBar(selected = selectedState)
                }

            ) {innerPadding->
                Column(modifier = Modifier.padding(innerPadding)) {

                    when(selectedState.value){
                        0 -> GeneralScreen(navController = navController, newsViewModel = newsViewModel)
                        1-> TechScreen(navController = navController,newsViewModel = newsViewModel)
                        2-> Sports(navController = navController,newsViewModel = newsViewModel)
                        3-> ScienceScreen(navController = navController,newsViewModel = newsViewModel)
                    }

                }
            }
        }


    }




    
}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(modifier=modifier
        .fillMaxWidth()
        .background(Color(0xFF471AA0))
        .padding(start = 16.dp, bottom = 80.dp, top = 16.dp)) {
        Column {
            Image(
                painter = painterResource(R.drawable.user_me),
                contentDescription = null,
                modifier= Modifier
                    .clip(CircleShape)
                    .size(60.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier= Modifier.height(10.dp))
            Text("Hello User!", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White)

        }
    }
    Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.Black))
}


@Composable
fun DrawerBody(onClick:()-> Unit,themeViewModel: ThemeViewModel,modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(false) }
    Column (modifier=modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(16.dp)){
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = {onClick()}).padding(bottom = 5.dp)) {
            Text("Sign Out", fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        Divider(Modifier.padding(top = 5.dp, bottom = 5.dp))

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = {}).padding(top = 5.dp, bottom = 5.dp)) {
            Text("Profile", fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        Divider(Modifier.padding(top = 5.dp, bottom = 5.dp))
        Spacer(Modifier.weight(1f))
        Row (verticalAlignment = Alignment.CenterVertically){
            var switchText=if(isChecked)"Light Mode" else "DarkMode"
            Text(switchText,fontSize = 18.sp, fontFamily = FontFamily.SansSerif)
            Spacer(Modifier.weight(1f))
            Switch(
                checked = isChecked,
                onCheckedChange = {
                    themeViewModel.toggleTheme()
                    isChecked=it
                }
            )

        }



    }
}