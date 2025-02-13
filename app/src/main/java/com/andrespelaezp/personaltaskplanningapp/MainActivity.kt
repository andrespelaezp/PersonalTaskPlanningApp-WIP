package com.andrespelaezp.personaltaskplanningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andrespelaezp.personaltaskplanningapp.di.viewmodelModule
import com.andrespelaezp.personaltaskplanningapp.ui.screens.main.AppMainScreen
import com.andrespelaezp.personaltaskplanningapp.ui.theme.TaskPlanningSourceLibraryTheme
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskPlanningSourceLibraryTheme {
                KoinApplication(application = {
                    modules(viewmodelModule)
                }) {
                    AppMainScreen()
                }
            }
        }
    }
}