package br.com.wale.getwater.model.repository

import br.com.wale.getwater.api.GetWaterApiService

/**
 * Created by Admin on 04/08/17.
 */
class ProductRepository(val apiService: GetWaterApiService){

    fun getProducts(storeId: Long) = apiService.getProducts(storeId)

}

object ProductRepositoryProvider{

    private var productRepository: ProductRepository? = null

    fun provideProductReposity() : ProductRepository{

        if(productRepository == null){
            productRepository = ProductRepository(GetWaterApiService.create())
        }

        return productRepository as ProductRepository
    }

}