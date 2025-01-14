package com.andrespelaezp.personaltaskplanningapp

import android.app.Application
import com.andrespelaezp.personaltaskplanningapp.di.viewmodelModule

class PersonalTaskPlanningAppApplication: Application() {

    private val modules = listOf(
        viewmodelModule
    )

    override fun onCreate() {
        super.onCreate()

        // Not needed due to KoinApplication for Compose
        // TODO: Delete this
//        startKoin {
//            modules
//        }
    }

}