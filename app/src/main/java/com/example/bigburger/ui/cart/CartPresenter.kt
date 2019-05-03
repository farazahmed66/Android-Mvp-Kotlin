package com.example.bigburger.ui.cart


class CartPresenter: CartContract.Presenter {

    private lateinit var view : CartContract.View

    override fun attach(view: CartContract.View) {
        this.view = view
    }
}