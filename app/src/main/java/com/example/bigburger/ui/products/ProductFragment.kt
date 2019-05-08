package com.example.bigburger.ui.products

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.bigburger.R
import com.example.bigburger.di.component.DaggerFragmentComponent
import com.example.bigburger.di.module.FragmentModule
import com.example.bigburger.model.CartModel
import com.example.bigburger.model.ProductModel
import com.example.bigburger.util.Utils
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class ProductFragment : Fragment(), ProductContract.View, ProductAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: ProductContract.Presenter
    private lateinit var rootView: View
    var count = 0

    fun newInstance(): ProductFragment {
        return ProductFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        rootView = inflater.inflate(R.layout.fragment_product, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    //On api data received setting adapter
    override fun loadDataSuccess(list: List<ProductModel>) {
        Utils.productList.clear()
        Utils.productList.addAll(list)
        val adapter = ProductAdapter(this.activity!!, list.toMutableList(), this)
        productRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        productRecyclerView!!.adapter = adapter
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    private fun initView() {
        presenter.loadData()
        activity?.btn_cart?.visibility = View.VISIBLE
        activity?.cart_count?.visibility = View.VISIBLE
    }

     override fun itemDetail(productModel: ProductModel) {
         addToCart(productModel)
         activity?.cart_count?.text = Utils.cartList.size.toString()
         Toast.makeText(rootView.context, "Item Added to cart", Toast.LENGTH_SHORT).show()
    }

    private fun addToCart(productModel: ProductModel) {

        val ref = productModel.ref
        var isAdded = false

        if(Utils.cartList.isNotEmpty()){
            Utils.cartList.forEach {
                if (it.ref == ref) {
                    isAdded = true
                }
            }
        }
        if(isAdded)
            updateItemInCart(ref)
        else
            addItemInCart(productModel)
    }

    private fun updateItemInCart(ref: Int) {
        Utils.cartList.forEachIndexed { index, element ->
            if (element.ref == ref) {
                Utils.cartList[index].qty++
                Utils.cartList[index].price = Utils.getItemTotal(ref,Utils.cartList[index].qty)
            }
        }
    }

    private fun addItemInCart(productModel: ProductModel) {
        val cart = CartModel(productModel.ref,
            productModel.title,
            productModel.description,
            productModel.thumbnail,
            productModel.price,
            productModel.qty)

        Utils.cartList.add(cart)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        listComponent.injectProductFragment(this)
    }

    override fun onResume() {
        super.onResume()
        activity?.cart_count?.text = Utils.cartList.size.toString()
    }

    companion object {
        const val TAG: String = "ProductFragment"
    }
}
