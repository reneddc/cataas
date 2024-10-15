package com.calyrsoft.frankyapp.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.calyrsoft.frankyapp.ui.theme.AppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                LoginUI()
            }
        }
    }
}

@Composable
fun LoginUI() {
    val loginViewModel = LoginViewModel()

    var userName by remember { mutableStateOf("")}
    var password by remember {mutableStateOf("") }

    val lifeCicleOwner = LocalLifecycleOwner.current
    val localContext = LocalContext.current

    fun updateUI(loginState: LoginViewModel.LoginState) {

        when ( loginState) {
            is LoginViewModel.LoginState.Loading -> {
                Toast.makeText(
                    localContext,
                    "Cargando",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoginViewModel.LoginState.DoLogin -> {
                Toast.makeText(
                    localContext,
                    loginState.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            is LoginViewModel.LoginState.Error -> {
                Toast.makeText(
                    localContext,
                    loginState.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            LoginViewModel.LoginState.LoggedOut -> {
                Toast.makeText(
                    localContext,
                    "LogOut",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
    loginViewModel.state.observe(
        lifeCicleOwner,
        Observer(::updateUI)

    )

    Scaffold {
        innerPadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = userName,
                    onValueChange = {
                        userName = it
                    }
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    visualTransformation = PasswordVisualTransformation()

                )
                Button(
                    onClick = {
                        loginViewModel.doLogin(
                            userName,
                            password
                        )
                    }
                ) {
                    Text(
                        text = "SignIn"
                    )
                }
            }
    }

}

@Preview
@Composable
fun preview() {
    LoginUI()
}

