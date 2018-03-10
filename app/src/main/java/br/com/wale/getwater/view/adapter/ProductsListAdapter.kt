package br.com.wale.getwater.view.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import br.com.wale.getwater.R
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_order.view.*

/**
 * Created by Admin on 04/08/17.
 */

class ProductsListAdapter(val view: AppContract.IOrderView, val products: ArrayList<Product>, listItemListener: ListItemListener) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>(){

    val listItemListener = listItemListener

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        var product = products[position]

        with(holder?.itemView!!){


            edtQuantity.tag = position
            edtQuantity.setText(product.quantity.toString())

            btnMinus.tag = position
            btnAdd.tag = position

            product?.let {

                txtProductName.text = product.name
                txtPrice.text = "R$ %.2f".format(product.price.toFloat()).replace(".", ",", true)

                Glide.with(context)
                        //.load(product.image)
                        .load("https://i2.wp.com/alonesurf.com.br/wp-content/uploads/2017/04/prancha.jpg?fit=800%2C674")
                        //.load("https://robohash.org/modidignissimosaut.png")
                        .centerCrop()
                        .into(imgViewProduct)

                edtQuantity.setText(product.quantity.toString())

            }

            btnMinus.setOnClickListener({ v: View ->

                listItemListener.onItemClick(v.parent, v, v.tag.toString().toInt())

            })

            btnAdd.setOnClickListener({ v:View ->

                listItemListener.onItemClick(v.parent, v, v.tag.toString().toInt())

            })

            edtQuantity.addTextChangedListener(object : TextWatcher{

                override fun afterTextChanged(p0: Editable?) {

                    listItemListener.onTextChanged(this@with.edtQuantity, this@with.edtQuantity.tag as Int)

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    println("on")
                }

            })

            if(edtQuantity.text.toString().toInt() == 0) {
                edtQuantity.setTextColor(ContextCompat.getColor(view.getContext(), android.R.color.darker_gray))
            }else{
                edtQuantity.setTextColor(ContextCompat.getColor(view.getContext(), android.R.color.black))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_order, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = products.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

}

interface ListItemListener{

    fun onItemClick(parentView: ViewParent, view: View, position: Int)

    fun onTextChanged(view: View?, position: Int)

}
