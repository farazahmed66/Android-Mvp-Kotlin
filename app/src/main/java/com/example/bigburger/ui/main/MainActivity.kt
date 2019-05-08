package com.example.bigburger.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.bigburger.R
import com.example.bigburger.di.component.DaggerActivityComponent
import com.example.bigburger.di.module.ActivityModule
import com.example.bigburger.model.ProductModel
import com.example.bigburger.ui.cart.CartFragment
import com.example.bigburger.ui.products.ProductFragment
import com.example.bigburger.util.Utils

import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View  {

    @Inject
    lateinit var presenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        btn_cart.setOnClickListener {
            if(Utils.cartList.isNullOrEmpty())
                return@setOnClickListener
            showCartFragment()
        }

        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    override fun showProductsFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, ProductFragment().newInstance(), ProductFragment.TAG)
            .commit()
    }

    override fun showCartFragment() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.frame, CartFragment().newInstance(), CartFragment.TAG)
            .commit()
    }

    override fun showProgress(show: Boolean) {
    }

    override fun showErrorMessage(error: String) {
    }

    override fun loadDataSuccess(list: List<ProductModel>) {
    }

}
