package br.com.wale.getwater.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.wale.getwater.R
import br.com.wale.getwater.SharedPref
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.Address
import br.com.wale.getwater.presenter.AddAddressPresenter
import br.com.wale.getwater.presenter.AddressesPresenter
import br.com.wale.getwater.util.showToast
import kotlinx.android.synthetic.main.activity_add_address.*

/**
 * Created by Admin on 19/08/17.
 */
class AddAddressActivity : BaseActivity(), AppContract.IAddAddressView{

    lateinit var presenter : AddAddressPresenter
    private lateinit var sharedPrefs : SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        title = resources.getString(R.string.activity_add_address_title)

        sharedPrefs = SharedPref(this)
        presenter = AddAddressPresenter(this)

        btnAddAddress.setOnClickListener{ view ->
            addAddresses(view)
        }
    }

    override fun addAddresses(view: View) {


        val address = hashMapOf(
                "address[street]" to edtStreet.text.toString(),
                "address[name]" to "casa",
                "address[neighborhood]" to edtNeighborhood.text.toString(),
                "address[number]" to edtNumber.text.toString(),
                "address[city]" to edtCity.text.toString(),
                "address[state]" to edtState.text.toString(),
                "address[cep]" to edtZipCode.text.toString(),
                "address[complement]" to edtComplement.text.toString())

            presenter.addAddress(address, sharedPrefs.getClient(), sharedPrefs.getAccessToken(), sharedPrefs.getUid())


    }

    override fun addressAdded(address: Address){

        if(address is Address){

            AlertDialog.Builder(this)
                    .setTitle("Sucesso")
                    .setMessage("Endereço cadastrado com sucesso!")
                    .setPositiveButton("OK") { p0, p1 ->

                        onBackPressed()
                    }
                    .setCancelable(true)
                    .show()

        }

    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun getContext(): Context {
        return this@AddAddressActivity
    }

}
