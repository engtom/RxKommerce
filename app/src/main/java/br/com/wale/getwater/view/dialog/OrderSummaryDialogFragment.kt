package br.com.wale.getwater.view.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import br.com.wale.getwater.R
import br.com.wale.getwater.model.entity.Product
import br.com.wale.getwater.view.adapter.OrderSummaryListAdapter
import kotlinx.android.synthetic.main.dialog_order_summary.*
import kotlinx.android.synthetic.main.dialog_order_summary.view.*
import java.text.NumberFormat

/**
 * Created by Admin on 15/08/17.
 */

class OrderSummaryDialogFragment : DialogFragment(){

    private var current = ""

    companion object {

        val TAG = "order_summary_dialog"//OrderSummaryDialogFragment::class.simpleName
        val EXTRA_ARRAY = "array_products"

        fun show(activity: Activity, list: ArrayList<Product>){

            OrderSummaryDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_ARRAY, list)
                }
            }.show(activity.fragmentManager, TAG)

        }


    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity.layoutInflater.inflate(R.layout.dialog_order_summary, null)
        val list = arguments.getParcelableArrayList<Product>(EXTRA_ARRAY)

        view?.let{

            var price = 0.0

            with(view.txtDescription){
//                layoutManager = LinearLayoutManager(activity)
//                setHasFixedSize(true)
//                adapter = OrderSummaryListAdapter(list)

                for(prod in list){
                    text = text.toString() + "${prod.quantity} ${prod.name}\n"
                    price += prod.price.toFloat() * prod.quantity
                }
            }

            with(view.txtTotalPrice){

                text = "R$ " + "%.2f".format(price).replace(".", ",", false)

            }

            view.edtCash.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                    var textWatcher = this

                    if(!view.edtCash.text.toString().equals(current)){

                        with(view.edtCash) {

                            removeTextChangedListener(textWatcher)

                            val replaceable: String = "%s".format(NumberFormat.getCurrencyInstance().currency.symbol)

                            //val cleanString = text.toString().replace(replaceable, "", true).replace(",", "", false).replace(".", "", false)
                            //val cleanString = Regex("[R$,.]").replace(text.toString(), "")
                            val cleanString = text.toString().replace(Regex("[R$,.]"), "")

                            val parsed = cleanString.toDouble()
                            val formatted = NumberFormat.getCurrencyInstance().format(parsed/100)

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

        return AlertDialog.Builder(activity)
                .setTitle("")
                .setView(view)
                .setPositiveButton("Finalizar"){
                    dialog, wich ->

                }
                .setNegativeButton("Cancelar"){
                    dialog, wich ->
                    this.dismiss()
                }

                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                }


    }

}