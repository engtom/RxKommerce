package br.com.wale.getwater.contract

import android.view.View
import br.com.wale.getwater.model.entity.*
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by Admin on 01/08/17.
 */
sealed class AppContract{

    interface ILoginView: BaseContract.IBaseView{
        fun loginSucceeded()
    }

    interface IMainView: BaseContract.IBaseView{

    }

    interface ISignUpView: BaseContract.IBaseView{
        fun signUpSucceeded()
    }

    interface IStoresView: BaseContract.IBaseView{
        fun showStores(stores: List<Store>)
    }

    interface IOrderView: BaseContract.IBaseView{
        fun showProducts(products: ArrayList<Product>)
    }

    interface IAddressesView: BaseContract.IBaseView{
        fun showAddresses(addresses: ArrayList<Address>)
    }

    interface IAddAddressView: BaseContract.IBaseView{
        fun addAddresses(view: View)
        fun addressAdded(address:Address)
    }

    interface IOrderSummaryView: BaseContract.IBaseView{
        fun showSummary()
        fun placeOrder(view: View)
        fun orderPlaced(order: Order)
    }

    interface ILoginPresenter: BaseContract.IBasePresenterView{
        fun login(login: String, password: String)
    }

    interface ISignUpPresenter: BaseContract.IBasePresenterView{
        fun addUser(name: String, email: String, password: String, confirmPassword: String)
    }

    interface IStoresPresenter: BaseContract.IBasePresenterView{
        fun getStores()
    }

    interface IOrderPresenter: BaseContract.IBasePresenterView{
        fun getProducts(storeId: Long)
    }

    interface IAddresssesPresenter: BaseContract.IBasePresenterView{
        fun getAddresses(page: Int, client: String, accessToken: String, uId: String)
    }

    interface IAddAddresssPresenter: BaseContract.IBasePresenterView{
        fun addAddress(address:Map<String, String>, client: String, accessToken: String, uId: String)
    }

    interface IOrderSummaryPresenter: BaseContract.IBasePresenterView{
        fun placeOrder(order:Map<String, String>, client: String, accessToken: String, uId: String)
    }

}