package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

/**
 * Created by Admin on 01/08/17.
 */
data class Store(@SerializedName("id")
                 val id: Long,
                 @SerializedName("name")
                 val name: String,
                 @SerializedName("logo")
                 val imageUrl: String): AutoParcelable