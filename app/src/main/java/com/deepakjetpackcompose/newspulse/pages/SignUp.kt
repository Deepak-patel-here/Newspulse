package com.deepakjetpackcompose.newspulse.pages

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deepakjetpackcompose.newspulse.R
import com.deepakjetpackcompose.newspulse.viewmodel.AuthViewModel
import com.deepakjetpackcompose.newspulse.viewmodel.AuthState


@Composable
fun SignUp(navController: NavController,authViewModel: AuthViewModel,modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isShow by remember { mutableStateOf(false) }
    val keyboardController= LocalSoftwareKeyboardController.current
    val myAuthState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(myAuthState) {
        when (myAuthState) {
            is AuthState.Authenticated -> navController.navigate("home"){
                popUpTo("signup"){inclusive=true}
            }
            is AuthState.Error -> {
                val errorMessage = (myAuthState as AuthState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    // ------------------------------------//
    val userNameFocus = remember { FocusRequester() }
    val emailFocus = remember { FocusRequester() }
    val passwordFocus = remember { FocusRequester() }
    val confirmPasswordFocus = remember { FocusRequester() }


    Column (modifier= modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)
    ){

        Box(modifier= Modifier.align(Alignment.Start)){
            BackBtn(navController = navController)
        }

        Column(modifier=modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {


            Box(modifier= Modifier.align(Alignment.Start)){
                Text("Sign Up", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF471AA0))
            }

            Spacer(Modifier.height(35.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = {userName=it},
                modifier= Modifier.fillMaxWidth().focusRequester(userNameFocus),
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(0xFF471AA0)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color(0xFF9747FF),
                    focusedIndicatorColor = Color.Magenta,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                label = {Text("UserName")},
                maxLines = 1,
                minLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        emailFocus.requestFocus()
                    }
                )
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {email=it},
                modifier= Modifier.fillMaxWidth().focusRequester(emailFocus),
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.email),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(0xFF471AA0)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color(0xFF9747FF),
                    focusedIndicatorColor = Color.Magenta,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                label = {Text("Email")},
                maxLines = 1,
                minLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocus.requestFocus()
                    }
                )

            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {password=it},
                modifier= Modifier.fillMaxWidth().focusRequester(passwordFocus),
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.password_locked),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(0xFF471AA0)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color(0xFF9747FF),
                    focusedIndicatorColor = Color.Magenta,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                label = {Text("Password")},
                trailingIcon = {
                    Icon(
                        painter = if(isShow)painterResource(R.drawable.show) else painterResource(R.drawable.hide),
                        contentDescription = null,
                        modifier=Modifier.size(20.dp).alpha(0.7f).clickable(onClick = {isShow = !isShow}),
                        tint = Color(0xFF000000)
                    )
                },
                visualTransformation =if(isShow) VisualTransformation.None else PasswordVisualTransformation(),
                maxLines = 1,
                minLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        confirmPasswordFocus.requestFocus()
                    }
                )
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {confirmPassword=it},
                modifier= Modifier.fillMaxWidth().focusRequester(confirmPasswordFocus),
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.password_locked),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(0xFF471AA0)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color(0xFF9747FF),
                    focusedIndicatorColor = Color.Magenta,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                label = {Text("Confirm Password")},
                trailingIcon = {
                    Icon(
                        painter = if(isShow)painterResource(R.drawable.show) else painterResource(R.drawable.hide),
                        contentDescription = null,
                        modifier=Modifier.size(20.dp).alpha(0.7f).clickable(onClick = {isShow = !isShow}),
                        tint = Color(0xFF000000)
                    )
                },
                visualTransformation =if(isShow) VisualTransformation.None else PasswordVisualTransformation(),
                maxLines = 1,
                minLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )



            Spacer(Modifier.height(70.dp))
            Button(onClick = {
                if(password == confirmPassword){
                    authViewModel.signUp(email,password)
                }else{
                    Toast.makeText(context,"Password doesn't match", Toast.LENGTH_SHORT).show()
                }

            },
                enabled = myAuthState != AuthState.Loading
                ,modifier= Modifier
                .fillMaxWidth()
                .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB84E8)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text("Sign Up", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }


            Spacer(Modifier.weight(1f))
            Row {
                Text("Already have an account ? ",
                    fontSize = 18.sp,
                    color = Color(0xFF471AA0)
                )
                Text("Sign In",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF471AA0),
                    modifier= Modifier.clickable(onClick = {
                        navController.navigate("login"){
                            popUpTo("signup"){inclusive=true}
                        }
                    })
                )
            }





        }
        




    }
}


@Composable
fun BackBtn(navController: NavController,modifier: Modifier = Modifier) {

    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            onClick = {
                navController.navigate("login"){
                    popUpTo("signup"){inclusive=true}
                }
            }
        )){
        Icon(
            painter = painterResource(R.drawable.left_arrow_two),
            contentDescription = null,
            tint = Color(0xFF471AA0),
            modifier = Modifier.size(17.dp)
        )
        Text("Back", color = Color(0xFF471AA0), fontSize = 15.sp)
    }



    
}