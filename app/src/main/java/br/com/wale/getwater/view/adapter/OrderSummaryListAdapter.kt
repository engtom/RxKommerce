package br.com.wale.getwater.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wale.getwater.R
import br.com.wale.getwater.model.entity.Product
import kotlinx.android.synthetic.main.list_item_order_summary.view.*

/**
 * Created by Admin on 16/08/17.
 */
class OrderSummaryListAdapter(val list: ArrayList<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val product = list[position]

        with(holder!!.itemView){
            txtProduct.text = "${product.quantity} ${product.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_order_summary, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount() =
            list.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

}
