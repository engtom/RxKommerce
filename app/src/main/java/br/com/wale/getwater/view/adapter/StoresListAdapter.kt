package br.com.wale.getwater.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wale.getwater.R
import br.com.wale.getwater.model.entity.Store
import br.com.wale.getwater.util.Constants
import br.com.wale.getwater.view.activity.OrderActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_store.view.*
//import com.bumptech.glide.request.RequestOptions


/**
 * Created by Admin on 31/07/17.
 */
class StoresListAdapter(val context: Context, val list: List<Store>) : RecyclerView.Adapter<StoresListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        var store = list[position]

        with(holder?.itemView!!){

//            val options = RequestOptions()
//                    .centerCrop()

            Glide.with(context)
//                    .apply { options }
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSA_OA4cTyi3sIkFqmwJ2wahSBvrEBwhY38BDWlHbKM_Z4MqAz_")
                    //.load(store.imageUrl)
                    .into(imgViewStore)

            txtStoreName.text = store.name

            setOnClickListener(View.OnClickListener {

                val intent = Intent(context, OrderActivity::class.java)
                intent.putExtra(Constants.EXTRA_STORE, store)
                context.startActivity(intent)

            })


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_store, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount() = list.size

    class ViewHolder(v: View): RecyclerView.ViewHolder(v)

}