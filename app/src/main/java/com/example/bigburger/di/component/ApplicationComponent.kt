package com.example.bigburger.di.component

import com.example.bigburger.BaseApp
import com.example.bigburger.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApp)
}