package com.example.bigburger.ui.base

class BaseContract {
    interface Presenter<in T>{
        fun attach(view: T)
    }
    interface View
}