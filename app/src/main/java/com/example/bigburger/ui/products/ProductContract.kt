package com.example.bigburger.ui.products

import com.example.bigburger.model.Product
import com.example.bigburger.ui.base.BaseContract

class ProductContract {
    interface View : BaseContract.View{
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Product>)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadData()
    }
}
