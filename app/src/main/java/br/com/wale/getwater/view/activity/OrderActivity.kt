package br.com.wale.getwater.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewParent
import android.widget.EditText
import android.widget.Toast
import br.com.wale.getwater.R
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.Product
import br.com.wale.getwater.model.entity.Store
import br.com.wale.getwater.presenter.OrderPresenter
import br.com.wale.getwater.util.Constants
import br.com.wale.getwater.util.showToast
import br.com.wale.getwater.view.adapter.ListItemListener
import br.com.wale.getwater.view.adapter.ProductsListAdapter
import br.com.wale.getwater.view.dialog.OrderSummaryDialogFragment
import kotlinx.android.synthetic.main.activity_order.*

/**
 * Created by Admin on 31/07/17.
 */
class OrderActivity : BaseActivity(), AppContract.IOrderView{

    private lateinit var store: Store

    lateinit var presenter: OrderPresenter
    lateinit var rvAdapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        title = resources.getString(R.string.activity_order_title)

        getExtras()

        store?.let{

            presenter = OrderPresenter(this@OrderActivity)
            presenter.getProducts(store.id)

            btnBuy.setOnClickListener({

                rvAdapter?.let {

                    var list = ArrayList<Product>()

                    for (prod in rvAdapter.products){

                        if(prod.quantity > 0){
                            list.add(prod)
                        }

                    }

                    val intent = Intent(this, AddressesActivity::class.java)
                    intent.putExtra(Constants.EXTRA_ARRAY_PRODUCTS, list)
                    intent.putExtra(Constants.EXTRA_STORE, store)

                    startActivity(intent)

                    //OrderSummaryDialogFragment.show(this, list)
                }

            })

            if(txtTotalPrice.text.toString().replace(",", ".").toFloat() > 0) {
                btnBuy.visibility = View.VISIBLE
            }else {
                btnBuy.visibility = View.GONE
            }

        }

        recyclerViewOrder.layoutManager = LinearLayoutManager(this@OrderActivity)
    }

    private fun getExtras(){

        val intent = intent

        intent?.let{

            store = intent.getParcelableExtra(Constants.EXTRA_STORE)

        }

    }

    override fun showProducts(products: ArrayList<Product>) {

        this.rvAdapter = ProductsListAdapter(this@OrderActivity, products, object: ListItemListener {

            override fun onItemClick(parentView: ViewParent, view: View, position: Int) {

                if(view.id == R.id.btnAdd){

                    products[position].quantity++

                }else if(view.id == R.id.btnMinus){

                    //var quantity = Integer.parseInt(edtQuantity.text.toString())
                    var quantity = products[position].quantity

                    if (quantity > 0) {

                        quantity -= 1

                        products[position].quantity = quantity
                    }

                }

                var subTotal = 0.0f

                for(product in products) {
                    subTotal += product.quantity * product.price.toFloat()
                }

                txtTotalPrice.text = "%.2f".format(subTotal).replace(".", ",", true)

                if(subTotal > 0) {
                    btnBuy.visibility = View.VISIBLE
                }else {
                    btnBuy.visibility = View.GONE
                }

                rvAdapter.notifyItemChanged(position)

            }

            override fun onTextChanged(view: View?, position: Int) {

                val edtText = view as EditText

                if(!edtText.text.toString().equals("")) {

                    products[position].quantity = edtText.text.toString().toInt()

                    var subTotal = 0.0f

                    for (product in products) {
                        subTotal += product.quantity * product.price.toFloat()
                    }

                    txtTotalPrice.text = "%.2f".format(subTotal).replace(".", ",", true)

                    if(subTotal > 0) {
                        btnBuy.visibility = View.VISIBLE
                    }else {
                        btnBuy.visibility = View.GONE
                    }

                    if(edtText.text.toString().toInt() == 0) {
                        edtText.setTextColor(ContextCompat.getColor(view.getContext(), android.R.color.darker_gray))
                    }else{
                        edtText.setTextColor(ContextCompat.getColor(view.getContext(), android.R.color.black))
                    }
                }

            }
        })

        with(recyclerViewOrder){
            setHasFixedSize(true)

            adapter = rvAdapter

        }

    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun getContext(): Context {
        return this@OrderActivity
    }

}