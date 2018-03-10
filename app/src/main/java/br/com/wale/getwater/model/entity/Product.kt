package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

/**
 * Created by Admin on 28/07/17.
 */

data class Product(
        @SerializedName("id")
        val onlineId: Long,
        @SerializedName("name")
        val name: String,
        val brand: String?,
        @SerializedName("price_cents")
        val price: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("description")
        val description: String?,
        var quantity: Int = 0) : AutoParcelable