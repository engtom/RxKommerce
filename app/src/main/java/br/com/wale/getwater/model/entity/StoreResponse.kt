package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Admin on 03/08/17.
 */
data class StoreResponse(@SerializedName("stores")
                         val list: List<Store>)