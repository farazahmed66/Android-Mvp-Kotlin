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
import com.example.bigburger.model.Product
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
    override fun loadDataSuccess(list: List<Product>) {
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

     override fun itemDetail(product: Product) {
         count++
         activity?.cart_count?.text = count.toString()
         Utils.cartList.add(product)
         Toast.makeText(rootView.context, "Item Added to cart", Toast.LENGTH_SHORT).show()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        listComponent.injectProductFragment(this)
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    companion object {
        const val TAG: String = "ProductFragment"
    }
}
