package br.com.wale.getwater.model.repository

import br.com.wale.getwater.api.GetWaterApiService

/**
 * Created by Admin on 21/08/17.
 */
class OrderRepository(val apiService: GetWaterApiService){

    fun placeOrder(order: Map<String, String>, client: String, accessToken: String, uId: String) =
            apiService.placeOrder(order, client, accessToken, uId)

    object OrderRepositoryProvider{

        private  var orderRepository : OrderRepository? = null

        fun provideOrderRepository() : OrderRepository{

            if(orderRepository == null){
                orderRepository = OrderRepository(GetWaterApiService.create())
            }

            return orderRepository as OrderRepository

        }

    }

}