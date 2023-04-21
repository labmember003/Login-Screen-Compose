package com.falcon.loginscreen_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falcon.loginscreen_compose.ui.theme.LoginScreenComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_background),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Card(
                    elevation = 4.dp,
                    border = BorderStroke(1.dp, colorResource(id = R.color.purple_blue)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    val screen = remember{ mutableStateOf(SCREEN.LOGIN) }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            val loginButtonColor = remember{ mutableStateOf(Color.Blue) }
                            if (screen.value == SCREEN.LOGIN) {
                                loginButtonColor.value = Color.Blue
                            } else {
                                loginButtonColor.value = Color.Black
                            }
                            Text(
                                text = "Login",
                                style = MaterialTheme.typography.h5,
                                color = loginButtonColor.value,
                                modifier = Modifier.clickable {
                                    screen.value = SCREEN.LOGIN
                                }
                            )
                            val signUpButtonColor = remember{ mutableStateOf(Color.Black) }
                            if (screen.value == SCREEN.SIGNUP) {
                                signUpButtonColor.value = Color.Blue
                            } else {
                                signUpButtonColor.value = Color.Black
                            }
                            Text(text = "SignUp",
                                style = MaterialTheme.typography.h5,
                                color = signUpButtonColor.value,
                                modifier = Modifier.clickable{
                                    screen.value = SCREEN.SIGNUP
                                }
                            )
                        }
                        if (screen.value == SCREEN.LOGIN) {
                            Login()
                        } else {
                            SignUp()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Login() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginImage()
        EditText("Username")
        EditText("Password", PasswordVisualTransformation())
        LoginButton("Login")
    }
}

@Composable
fun SignUp() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginImage()
        EditText("Username")
        EditText("Email")
        EditText("Password", PasswordVisualTransformation())
        LoginButton("SignUp")
    }
}

@Composable
fun LoginImage() {
    Image(
        painter = painterResource(id = R.drawable.login_img),
        contentDescription = "",
        modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp)
            .scale(1.5f)
//            .size(150.dp)
    )
}

@Composable
fun EditText(text: String, visualTransformation: VisualTransformation = VisualTransformation.None) {
    val content = remember { mutableStateOf("") }
    OutlinedTextField(
        value = content.value,
        onValueChange = {
            content.value = it
        },
        label = { Text(text) },
        modifier = Modifier.padding(16.dp),
        visualTransformation = visualTransformation
    )
}

@Composable
fun LoginButton(text: String) {
    Button(onClick = {
        // Implement Later
    }) {
        Text(text = text)
    }
}

enum class SCREEN {
    LOGIN, SIGNUP
}
