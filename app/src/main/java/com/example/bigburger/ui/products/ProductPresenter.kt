package com.example.bigburger.ui.products

import com.example.bigburger.api.ApiServiceInterface
import com.example.bigburger.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductPresenter: ProductContract.Presenter {


    private val products = CompositeDisposable()
    private val api : ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view : ProductContract.View

    //Calling api function
    override fun loadData() {
        val product = api.getProductList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({list : List<ProductModel>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            },{error->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })
        products.add(product)
    }

    override fun attach(view: ProductContract.View) {
        this.view = view
    }
}