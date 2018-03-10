package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

/**
 * Created by Admin on 29/07/17.
 */
data class UserResponse(@SerializedName("data") val user: User)