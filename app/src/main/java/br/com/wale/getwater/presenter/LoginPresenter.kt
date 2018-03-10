package br.com.wale.getwater.presenter

import br.com.wale.getwater.SharedPref
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.repository.UserRepository
//import br.com.wale.getwater.model.repository.UserRepositoryProvider
import br.com.wale.getwater.view.activity.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Admin on 01/08/17.
 */
class LoginPresenter(val view: AppContract.ILoginView) : AppContract.ILoginPresenter{

    private val compositeDisposables = CompositeDisposable()

    override fun login(login: String, password: String) {

        val repository = UserRepository.UserRepositoryProvider.provideUserRepository()

        repository.login(login, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe (
                    {
                        it?.let {
                            if(it.code() == 200){

                                val accessToken = it.headers().get("access-token")
                                val client = it.headers().get("client")
                                val uid = it.headers().get("uid")

                                val sharedPrefs = SharedPref(view.getContext())

                                with(sharedPrefs){
                                    setAccessToken(accessToken)
                                    setClient(client)
                                    setUid(uid)
                                }


                                view.loginSucceeded()

                            }else{

                                view.showError(it.message())

                            }
                        }
                    },
                    {
                        println("error")
                        view.showError(it.message!!)
                    },
                    {
                        println("completed")
                    }
                ).to(compositeDisposables)




    }

    override fun onDestroy() {
        this.compositeDisposables.clear()
    }


}