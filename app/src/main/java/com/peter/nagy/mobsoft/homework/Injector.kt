package com.peter.nagy.mobsoft.homework

import android.app.Activity
import android.support.v4.app.Fragment

val Activity.injector: MoviesApplicationComponent
    get() {
        return (this.applicationContext as MoviesApplication).injector
    }

val Fragment.injector: MoviesApplicationComponent
    get() {
        return (this.context!!.applicationContext as MoviesApplication).injector
    }