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
import com.example.bigburger.model.CartModel
import com.example.bigburger.ui.products.ProductAdapter
import com.example.bigburger.util.Utils
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class CartFragment : Fragment(), CartContract.View, CartAdapter.OnItemClickListener {


    @Inject
    lateinit var presenter: CartContract.Presenter
    lateinit var adapter: CartAdapter

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
        adapter = CartAdapter(this.activity!!, Utils.cartList.toMutableList(), this)
        cartRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        cartRecyclerView!!.adapter = adapter

        totalPrice.text = Utils.getTotal()
    }

    override fun qtyDecrease(ref: Int) {
        Utils.cartList.forEachIndexed { index, element ->
            if (element.ref == ref) {
                Utils.cartList[index].qty--
                Utils.cartList[index].price = Utils.getItemTotal(ref,Utils.cartList[index].qty)
            }
            adapter.notifyDataSetChanged()
            totalPrice.text = Utils.getTotal()
        }
    }

    override fun qtyIncrease(ref: Int) {
        Utils.cartList.forEachIndexed { index, element ->
            if (element.ref == ref) {
                Utils.cartList[index].qty++
                Utils.cartList[index].price = Utils.getItemTotal(ref,Utils.cartList[index].qty)
            }
            adapter.notifyDataSetChanged()
            totalPrice.text = Utils.getTotal()
        }
    }

    override fun deleteItem(ref: CartModel) {
        Utils.cartList.forEachIndexed { index, element ->
            if (element.ref == ref.ref) {
                Utils.cartList.remove(ref)
                adapter.notifyItemRemoved(index)
            }
            adapter.notifyDataSetChanged()
            totalPrice.text = Utils.getTotal()
        }
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
