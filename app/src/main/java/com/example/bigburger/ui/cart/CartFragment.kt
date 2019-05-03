package com.example.bigburger.ui.cart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bigburger.R
import com.example.bigburger.di.component.DaggerFragmentComponent
import com.example.bigburger.di.module.FragmentModule
import com.example.bigburger.util.Utils
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class CartFragment : Fragment(), CartContract.View {

    @Inject
    lateinit var presenter: CartContract.Presenter

    private lateinit var rootView: View
    val cartList = Utils.cartList

    fun newInstance(): CartFragment {
        return CartFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
            rootView = inflater.inflate(R.layout.fragment_cart, container, false)

            return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    private fun initView() {
        activity?.btn_cart?.visibility = View.GONE
        activity?.cart_count?.visibility = View.GONE

        //initializing recycler View
        val adapter = CartAdapter(this.activity!!, cartList.toMutableList())
        cartRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        cartRecyclerView!!.adapter = adapter

        totalPrice.text = Utils.getTotal()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        listComponent.injectCartFragment(this)
    }
    companion object {
        const val TAG: String = "CartFragment"
    }

}
