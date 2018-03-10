package br.com.wale.getwater.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wale.getwater.R
import br.com.wale.getwater.model.entity.Address
import kotlinx.android.synthetic.main.list_item_address.view.*

/**
 * Created by Admin on 18/08/17.
 */
class AddressesListAdapter(val list: ArrayList<Address>, val itemClickListener: AddressListAdapterClickListener): RecyclerView.Adapter<AddressesListAdapter.ViewHolder>(){

    //val listener = itemClickListener

    interface AddressListAdapterClickListener{

        fun onItemClicked(v: View, address: Address){

        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val address = list[position]

        with(holder!!.itemView){

            txtAddress.setText(address.fullAddress()) //set the address

            setOnClickListener({

                itemClickListener.onItemClicked(it, address)

            })

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_address, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount() = list.size


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}