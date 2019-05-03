package com.example.bigburger.ui.cart

import com.example.bigburger.ui.base.BaseContract

class CartContract {
    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}