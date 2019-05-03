package com.example.bigburger.ui.main

import com.example.bigburger.ui.base.BaseContract
import com.example.bigburger.ui.products.ProductContract

class MainContract {

    interface View : BaseContract.View, ProductContract.View {
        fun showProductsFragment()
        fun showCartFragment()
    }

    interface Presenter : BaseContract.Presenter<View>
}
