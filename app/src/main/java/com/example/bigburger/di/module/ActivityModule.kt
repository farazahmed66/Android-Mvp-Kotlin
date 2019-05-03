package com.example.bigburger.di.module

import android.app.Activity
import com.example.bigburger.ui.main.MainContract
import com.example.bigburger.ui.main.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private var activity: Activity){
    @Provides
    fun provideActivity() : Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter{
        return MainPresenter()
    }
}