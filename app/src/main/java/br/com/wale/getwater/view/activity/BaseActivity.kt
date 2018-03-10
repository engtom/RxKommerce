package br.com.wale.getwater.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Admin on 31/07/17.
 */
abstract class BaseActivity: AppCompatActivity() {

    protected val contentDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.contentDisposables.clear()
    }

}