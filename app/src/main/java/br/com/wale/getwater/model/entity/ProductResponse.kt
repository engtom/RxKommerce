package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Admin on 04/08/17.
 */

data class ProductResponse(@SerializedName("products") val list: ArrayList<Product>)
