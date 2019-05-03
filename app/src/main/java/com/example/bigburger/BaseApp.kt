package com.example.bigburger

import android.app.Application
import com.example.bigburger.di.component.ApplicationComponent
import com.example.bigburger.di.component.DaggerApplicationComponent
import com.example.bigburger.di.module.ApplicationModule

class BaseApp : Application() {
    lateinit var component : ApplicationComponent

    companion object {
        lateinit var instance : BaseApp private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
        if (BuildConfig.DEBUG){

        }
    }

    private fun setup(){
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent{
        return component
    }
}