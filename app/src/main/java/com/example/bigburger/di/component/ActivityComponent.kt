package com.example.bigburger.di.component

import com.example.bigburger.di.module.ActivityModule
import com.example.bigburger.ui.main.MainActivity
import dagger.Component

    @Component(modules = arrayOf(ActivityModule::class))
    interface ActivityComponent {
        fun inject(mainActivity: MainActivity)
    }
