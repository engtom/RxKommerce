package br.com.wale.getwater.presenter

import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.repository.ProductRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Admin on 04/08/17.
 */
class OrderPresenter(val view: AppContract.IOrderView): AppContract.IOrderPresenter{

    private val compositeDisposables = CompositeDisposable()

    override fun getProducts(storeId: Long) {

        val repository = ProductRepositoryProvider.provideProductReposity()

        repository.getProducts(storeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {

                            if(it.code() == 200){

                                view.showProducts(it.body()!!.list)

                            }else{
                                view.showError(it.message())
                            }

                        },
                        {
                            view.showError(it.message ?: "Error")
                        }
                )

    }

    override fun onDestroy() {
        compositeDisposables.clear()
    }

}