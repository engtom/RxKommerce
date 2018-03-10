package br.com.wale.getwater.presenter

import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.repository.AddressRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Admin on 20/08/17.
 */
class AddAddressPresenter(val view: AppContract.IAddAddressView) : AppContract.IAddAddresssPresenter{

    private val compositeDisposables = CompositeDisposable()

    override fun addAddress(address: Map<String, String>, client: String, accessToken: String, uId: String) {

        val repository = AddressRepository.AddressRepositoryProvider.provideAddressRepository()

        repository.addAddres(address, client, accessToken, uId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if(it.code() == 200 || it.code() == 201 ){
                                view.addressAdded(it.body()!!)
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
