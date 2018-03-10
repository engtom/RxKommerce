package br.com.wale.getwater.model.repository

import br.com.wale.getwater.api.GetWaterApiService

/**
 * Created by Admin on 03/08/17.
 */
class StoreRepository(val apiService: GetWaterApiService){

    fun getStores() = apiService.getStores()

    object StoreRepositoryProvider{

        private  var storeRepository : StoreRepository? = null

        fun provideStoreRepository() : StoreRepository{

            if(storeRepository == null){
                storeRepository = StoreRepository(GetWaterApiService.create())
            }

            return storeRepository as StoreRepository

        }

    }

}
