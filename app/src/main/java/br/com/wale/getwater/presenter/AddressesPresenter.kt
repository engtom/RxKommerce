package br.com.wale.getwater.presenter

import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.repository.AddressRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Admin on 18/08/17.
 */
class AddressesPresenter(val view: AppContract.IAddressesView): AppContract.IAddresssesPresenter{

    private val compositeDisposables = CompositeDisposable()

    override fun getAddresses(page: Int, clientId: String, accessToken: String, uId: String) {

        val repository = AddressRepository.AddressRepositoryProvider.provideAddressRepository()

        repository.getAddress(page, clientId, accessToken, uId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if(it.code() == 200){

                                view.showAddresses(it.body()!!.list)

                            }else{

                                view.showError(it.message())

                            }

                        },
                        {
                            view.showError(it.message!!)
                        }
                )
                .to(compositeDisposables)

    }

    override fun onDestroy() {
        this.compositeDisposables.clear()
    }
}
