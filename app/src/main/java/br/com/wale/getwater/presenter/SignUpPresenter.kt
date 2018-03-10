package br.com.wale.getwater.presenter

import br.com.wale.getwater.SharedPref
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.repository.UserRepository
//import br.com.wale.getwater.model.repository.UserRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Admin on 02/08/17.
 */
class SignUpPresenter(val view: AppContract.ISignUpView) : AppContract.ISignUpPresenter{

    private val compositeDisposables = CompositeDisposable()

    override fun addUser(name: String, email: String, password: String, confirmPassword: String) {

        val repository = UserRepository.UserRepositoryProvider.provideUserRepository()

        repository.addUser(name, email, password, confirmPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            if(it.code() == 200 || it.code() == 201){

                                val accessToken = it.headers().get("access-token")
                                val client = it.headers().get("client")
                                val uid = it.headers().get("uid")

                                val sharedPrefs = SharedPref(view.getContext())

                                with(sharedPrefs){
                                    setAccessToken(accessToken!!)
                                    setClient(client!!)
                                    setUid(uid!!)
                                }

                                view.signUpSucceeded()

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