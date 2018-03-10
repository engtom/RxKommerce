package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Admin on 31/07/17.
 */
class AddressResponse(@SerializedName("addresses")val list: ArrayList<Address>)
