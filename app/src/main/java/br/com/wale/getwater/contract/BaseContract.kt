package br.com.wale.getwater.contract

import android.content.Context

/**
 * Created by Admin on 01/08/17.
 */
sealed class BaseContract{

    interface IBaseView{
        fun getContext() : Context
        fun showError(error: String)
    }

    interface IBasePresenterView{
        fun onDestroy()
    }

}