package br.com.wale.getwater.view.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.wale.getwater.R
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.model.entity.Store
import br.com.wale.getwater.presenter.StoresPresenter
import br.com.wale.getwater.util.showToast
import br.com.wale.getwater.view.adapter.StoresListAdapter
import br.com.wale.getwater.view.extras.RecyclerViewDivider
import kotlinx.android.synthetic.main.activity_stores.*

/**
 * Created by Admin on 03/08/17.
 */
class StoresActivity: BaseActivity(), AppContract.IStoresView{

    lateinit var presenter: StoresPresenter
    lateinit var adapter: StoresListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        title = resources.getString(R.string.activity_stores_title)

        presenter = StoresPresenter(this)

        presenter.getStores()

        recyclerViewStores.layoutManager = LinearLayoutManager(this@StoresActivity)
        recyclerViewStores.addItemDecoration(RecyclerViewDivider(this@StoresActivity, R.drawable.recycler_view_divider), 0)

    }

    override fun showStores(stores: List<Store>) {

        //adapter = StoresListAdapter(this@StoresActivity, stores)
        with(recyclerViewStores){

            //layoutManager = LinearLayoutManager(this@StoresActivity)
            //addItemDecoration(RecyclerViewDivider(this@StoresActivity))
            setHasFixedSize(true)
            adapter = StoresListAdapter(this@StoresActivity, stores)


        }

        //adapter.notifyDataSetChanged()

    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun getContext(): Context {
        return this
    }

}
