package com.example.questapi_248

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.questapi_248.ui.theme.QuestAPI_248Theme
import com.example.questapi_248.uicontroller.DataSiswaApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QuestAPI_248Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DataSiswaApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
