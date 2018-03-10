package br.com.wale.getwater.view.activity

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import br.com.wale.getwater.R
import br.com.wale.getwater.SharedPref
import br.com.wale.getwater.contract.AppContract

class MainActivity : BaseActivity(), AppContract.IMainView {

    private lateinit var txtView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtView = findViewById(R.id.txtViewToken)

        val prefs = SharedPref(this@MainActivity)

        txtView.setText(prefs.getAccessToken())
    }

    override fun showError(msg: String){


    }

    override fun getContext(): Context {
        return this@MainActivity
    }
}
