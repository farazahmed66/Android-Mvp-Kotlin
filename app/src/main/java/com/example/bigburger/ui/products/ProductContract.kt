package com.example.bigburger.ui.products

import com.example.bigburger.model.ProductModel
import com.example.bigburger.ui.base.BaseContract

class ProductContract {
    interface View : BaseContract.View{
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<ProductModel>)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadData()
    }
}
