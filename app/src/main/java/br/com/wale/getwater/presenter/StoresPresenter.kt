package br.com.wale.getwater.presenter

import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.StoreResponse
import br.com.wale.getwater.model.repository.StoreRepository
//import br.com.wale.getwater.model.repository.StoreRepositoryProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by Admin on 03/08/17.
 */
class StoresPresenter(val view: AppContract.IStoresView) : AppContract.IStoresPresenter {

    private val compositeDisposables = CompositeDisposable()

    override fun getStores() {

        val repository = StoreRepository.StoreRepositoryProvider.provideStoreRepository()

        repository.getStores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if(it.code() == 200){

                                view.showStores(it.body()!!.list)

                            }else{
                                view.showError(it.message())
                            }

                        },
                        {
                        }
                )
                .to(compositeDisposables)

    }

    override fun onDestroy() {
        compositeDisposables.clear()
    }
}