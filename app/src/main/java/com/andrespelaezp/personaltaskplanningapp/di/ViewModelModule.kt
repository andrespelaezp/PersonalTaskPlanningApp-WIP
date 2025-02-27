package com.andrespelaezp.personaltaskplanningapp.di

import com.andrespelaezp.datasourcecompiler.di.databaseModule
import com.andrespelaezp.datasourcecompiler.di.dispatcherModule
import com.andrespelaezp.datasourcecompiler.di.apiModule
import com.andrespelaezp.datasourcecompiler.di.repositoryModule
import com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard.DashboardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {

    includes(dispatcherModule, apiModule, databaseModule, repositoryModule)

    viewModelOf(::DashboardViewModel)
}