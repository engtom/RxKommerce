package br.com.wale.getwater.presenter

import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.repository.OrderRepository
import br.com.wale.getwater.model.repository.ProductRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Admin on 04/08/17.
 */
class OrderSummaryPresenter(val view: AppContract.IOrderSummaryView): AppContract.IOrderSummaryPresenter {

    private val compositeDisposables = CompositeDisposable()

    override fun placeOrder(order: Map<String, String>, client: String, accessToken: String, uId: String) {

        val repository = OrderRepository.OrderRepositoryProvider.provideOrderRepository()

        repository.placeOrder(order, client, accessToken, uId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {

                            if(it.code() == 200 || it.code() == 201){

                                view.orderPlaced(it.body()!!)

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