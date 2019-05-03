package com.example.bigburger.di.module

import com.example.bigburger.api.ApiServiceInterface
import com.example.bigburger.ui.cart.CartContract
import com.example.bigburger.ui.cart.CartPresenter
import com.example.bigburger.ui.products.ProductContract
import com.example.bigburger.ui.products.ProductPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideCartPresenter(): CartContract.Presenter{
        return CartPresenter()
    }

    @Provides
    fun provideProductPresenter(): ProductContract.Presenter{
        return ProductPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface{
        return ApiServiceInterface.create()
    }
}
