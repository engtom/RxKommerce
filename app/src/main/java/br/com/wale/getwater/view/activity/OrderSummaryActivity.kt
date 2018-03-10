package br.com.wale.getwater.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import br.com.wale.getwater.R
import br.com.wale.getwater.SharedPref
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.Address
import br.com.wale.getwater.model.entity.Order
import br.com.wale.getwater.model.entity.Product
import br.com.wale.getwater.model.entity.Store
import br.com.wale.getwater.presenter.OrderSummaryPresenter
import br.com.wale.getwater.util.Constants
import br.com.wale.getwater.util.showToast
import kotlinx.android.synthetic.main.activity_order_summary.*

/**
 * Created by Admin on 21/08/17.
 */
class OrderSummaryActivity: BaseActivity(), AppContract.IOrderSummaryView{

    private var current = ""
    private var products = ArrayList<Product>()
    lateinit var address: Address
    lateinit var store: Store
    private lateinit var presenter : OrderSummaryPresenter
    private lateinit var sharedPrefs : SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        title = resources.getString(R.string.activity_order_summary_title)

        sharedPrefs = SharedPref(this)
        presenter = OrderSummaryPresenter(this@OrderSummaryActivity)

        getExtras()
        showSummary()

        btnPlaceOrder.setOnClickListener({

            placeOrder(it)

        })

        edtCash.setText("R$0,00")
        edtCash.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                var textWatcher = this

                if(!edtCash.text.toString().equals(current)){

                    with(edtCash) {

                        removeTextChangedListener(textWatcher)

                        val replaceable: String = "%s".format(java.text.NumberFormat.getCurrencyInstance().currency.symbol)

                        val cleanString = text.toString().replace(kotlin.text.Regex("[R$,.]"), "")

                        val parsed = cleanString.toDouble()
                        val formatted = java.text.NumberFormat.getCurrencyInstance().format(parsed/100)

                        current = formatted

                        setText(formatted)
                        setSelection(formatted.length)

                        addTextChangedListener(textWatcher)
                    }

                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun getExtras(){

        val args = intent.extras

        args.getParcelableArrayList<Product>(Constants.EXTRA_ARRAY_PRODUCTS)?.let {
            products = args.getParcelableArrayList<Product>(Constants.EXTRA_ARRAY_PRODUCTS)
        }

        args.getParcelable<Address>(Constants.EXTRA_ADDRESS)?.let {
            address = args.getParcelable(Constants.EXTRA_ADDRESS)
        }

        args.getParcelable<Store>(Constants.EXTRA_STORE)?.let {
            store = args.getParcelable(Constants.EXTRA_STORE)
        }

    }

    override fun showSummary() {

        var price = 0.0

        with(txtOrderSummary){

            for(prod in products){
                text = text.toString() + "${prod.quantity} ${prod.name}\n"
                price += prod.price.toFloat() * prod.quantity
            }
        }

        with(txtTotalPrice){

            text = "R$ " + "%.2f".format(price).replace(".", ",", false)

        }

        txtDeliveryAddress.text = address.fullAddress()

    }

    override fun placeOrder(view: View) {

        if(validate()) {

            if (products.size > 0) {

                var hashMap = HashMap<String, String>()

                for ((index, product) in products.withIndex()) {

                    hashMap.put("order[order_items_attributes][${index + 1}][product_id]", product.onlineId.toString())
                    hashMap.put("order[order_items_attributes][${index + 1}][quantity]", product.quantity.toString())

                }

                hashMap.put("order[store_id]", store.id.toString())
                hashMap.put("order[address_id]", address.onlineId.toString())
                hashMap.put("order[change_for_cents]", edtCash.text.toString().replace("R$", "", true).replace(",", ".", true))

                presenter.placeOrder(hashMap, sharedPrefs.getClient(), sharedPrefs.getAccessToken(), sharedPrefs.getUid())
            }

        }else{
            AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage("O valor para o troco deve ser maior que o valor total da compra.")
                    .setPositiveButton("OK") { p0, p1 ->

                        p0.dismiss()
                    }
                    .setCancelable(true)
                    .show()
        }

    }

    private fun validate() : Boolean{


        return edtCash.text.toString().isEmpty() || edtCash.text.toString().equals("R$0,00") || current.replace(kotlin.text.Regex("[R$]"), "").replace(",", ".").toDouble() >
                txtTotalPrice.text.toString().replace(Regex("[R$]"), "").replace(",", ".").toDouble()


    }

    override fun orderPlaced(order: Order) {

        order?.let {

            AlertDialog.Builder(this)
                    .setTitle("Sucesso")
                    .setMessage("Pedido realizado com sucesso. Os produtos serão entregues no endereço escolhido.")
                    .setPositiveButton("OK") { p0, p1 ->

                        val intent = Intent(this@OrderSummaryActivity, StoresActivity::class.java)

                        startActivity(intent)
                    }
                    .setCancelable(true)
                    .show()

        }

    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun getContext(): Context {
        return this@OrderSummaryActivity
    }


}