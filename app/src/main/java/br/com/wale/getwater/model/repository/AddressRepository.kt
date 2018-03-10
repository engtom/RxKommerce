package br.com.wale.getwater.model.repository

import br.com.wale.getwater.api.GetWaterApiService

/**
 * Created by Admin on 31/07/17.
 */
class AddressRepository(val apiService: GetWaterApiService){

    fun getAddress(page: Int, client: String, accessToken: String, uId: String) =
         apiService.getAddress(page, client, accessToken, uId)

    fun addAddres(address:Map<String, String>, client: String, accessToken: String, uId: String) =
            apiService.addAddress(address, client, accessToken, uId)

    object AddressRepositoryProvider{

        private  var addresRepository : AddressRepository? = null

        fun provideAddressRepository() : AddressRepository{

            if(addresRepository == null){
                addresRepository = AddressRepository(GetWaterApiService.create())
            }

            return addresRepository as AddressRepository

        }

    }

}