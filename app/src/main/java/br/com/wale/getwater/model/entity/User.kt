package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

/**
 * Created by Admin on 28/07/17.
 */

data class User(
        @SerializedName("id")
        var onlineId: Long,
        @SerializedName("login")
        var name: String?,
        @SerializedName("provider")
        var provider: String?,
        @SerializedName("image")
        var imageUrl: String?,
        var gender: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("uid")
        var uid: String?,
        @SerializedName("phone")
        var phone: String?,
        var password: String?)