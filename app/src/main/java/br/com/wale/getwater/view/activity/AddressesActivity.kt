package br.com.wale.getwater.view.activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import br.com.wale.getwater.R
import br.com.wale.getwater.SharedPref
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.Address
import br.com.wale.getwater.model.entity.Product
import br.com.wale.getwater.model.entity.Store
import br.com.wale.getwater.presenter.AddressesPresenter
import br.com.wale.getwater.util.Constants
import br.com.wale.getwater.util.showToast
import br.com.wale.getwater.view.adapter.AddressesListAdapter
import br.com.wale.getwater.view.extras.RecyclerViewDivider
import kotlinx.android.synthetic.main.activity_choose_address.*
import kotlinx.android.synthetic.main.activity_stores.*

/**
 * Created by Admin on 18/08/17.
 */
class AddressesActivity: BaseActivity(), AppContract.IAddressesView{

    lateinit var presenter: AddressesPresenter
    lateinit var adapter : AddressesListAdapter
    private lateinit var sharedPrefs : SharedPref
    private lateinit var store: Store
    private lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_address)

        title = resources.getString(R.string.activity_addresses_title)

        getExtras()

        sharedPrefs = SharedPref(this)
        presenter = AddressesPresenter(this@AddressesActivity)

        with(rvAddressses) {
            layoutManager = LinearLayoutManager(this@AddressesActivity)
            addItemDecoration(RecyclerViewDivider(this@AddressesActivity, R.drawable.recycler_view_divider), 0)
//            addOnItemTouchListener(object: RecyclerView.OnItemTouchListener{
//                override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//            })
        }

    }

    private fun getAddress(){
        presenter.getAddresses(0, sharedPrefs.getClient(), sharedPrefs.getAccessToken(), sharedPrefs.getUid())
    }

    private fun getExtras(){

        val args = intent.extras

        args.getParcelable<Store>(Constants.EXTRA_STORE)?.let {
            store = args.getParcelable(Constants.EXTRA_STORE)
        }

        args.getParcelableArrayList<Product>(Constants.EXTRA_ARRAY_PRODUCTS)?.let {
            products = args.getParcelableArrayList(Constants.EXTRA_ARRAY_PRODUCTS)
        }

    }

    fun newAddress(v: View){

        val intent = Intent(this@AddressesActivity, AddAddressActivity::class.java)

        startActivity(intent)

    }

    override fun showAddresses(addresses: ArrayList<Address>) {

        with(rvAddressses){
            setHasFixedSize(true)
            adapter = AddressesListAdapter(addresses, object: AddressesListAdapter.AddressListAdapterClickListener {

                override fun onItemClicked(v: View, address: Address) {

                    val intent = Intent(this@AddressesActivity, OrderSummaryActivity::class.java)

                    intent.putExtra(Constants.EXTRA_ADDRESS, address)
                    intent.putExtra(Constants.EXTRA_STORE, store)
                    intent.putExtra(Constants.EXTRA_ARRAY_PRODUCTS, products)

                    startActivity(intent)

                }

            })
        }

        if(addresses.size == 0){

            AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage("Você não tem endereço cadastrado. Deseja cadastrar agora?")
                    .setPositiveButton("Sim") { p0, p1 ->

                        val intent = Intent(this@AddressesActivity, AddAddressActivity::class.java)

                        startActivity(intent)
                    }
                    .setNegativeButton("Não", {
                        dialog, p1 -> dialog.dismiss()
                    })
                    .setCancelable(true)
                    .show()

        }

    }

    override fun showError(error: String) {

        showToast(error)

    }

    override fun getContext(): Context {
        return this@AddressesActivity
    }

    override fun onResume() {
        super.onResume()
        getAddress()
    }

}
