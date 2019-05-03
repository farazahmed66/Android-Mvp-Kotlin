package com.example.bigburger.di.component

import com.example.bigburger.di.module.FragmentModule
import com.example.bigburger.ui.cart.CartFragment
import com.example.bigburger.ui.products.ProductFragment
import dagger.Component


@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun injectProductFragment(productFragment: ProductFragment)
    fun injectCartFragment(cartFragment: CartFragment)

}