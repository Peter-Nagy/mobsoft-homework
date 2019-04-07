package com.peter.nagy.mobsoft.homework

import android.app.Application
import com.peter.nagy.mobsoft.homework.ui.UIModule

class MoviesApplication : Application() {
    lateinit var injector: MoviesApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerMoviesApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}