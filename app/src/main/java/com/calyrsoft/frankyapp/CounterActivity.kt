package com.calyrsoft.frankyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.calyrsoft.frankyapp.ui.theme.AppTheme

class CounterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                CounterUI()
            }
        }
    }
}

@Composable
fun CounterUI() {
    val counterViewModel = CounterViewModel()
    var counterString by remember { mutableStateOf("0") }
    val lifecycleOwner = LocalLifecycleOwner.current
    fun updateUi(s: String) {
        counterString = s
    }
    counterViewModel.cadena.observe(
        lifecycleOwner,
        Observer(::updateUi)
    )
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Row {
                Text(text = "Counter")
                Text(text = counterString)
            }
            Button(onClick = {
                counterViewModel.increment()
            }) {
                Text(text = "Increment")
            }

        }
    }
}

