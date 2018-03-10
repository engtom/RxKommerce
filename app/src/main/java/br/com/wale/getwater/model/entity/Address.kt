package br.com.wale.getwater.model.entity

import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

/**
 * Created by Admin on 28/07/17.
 */

data class Address(@SerializedName("id") var onlineId: Long,
                   var street: String?,
                   var number: String?,
                   var complement: String?,
                   var neighborhood: String?,
                   var city: String?,
                   var state: String?,
                   @SerializedName("cep") var zipCode: String?,
                   var latitude: String?,
                   var longitude: String?,
                   var type: Int?,
                   var userId: Long ): AutoParcelable {

    fun fullAddress() : String{

        var fullAddress = ""

        if(this.zipCode != ""){
            fullAddress += this.zipCode + " \n"
        }

        if(this.street != ""){
            fullAddress += this.street

            if(this.number.isNullOrEmpty()){
                fullAddress += ", " + this.number
            }

            if(!this.complement.isNullOrEmpty()){
                fullAddress += ", " + this.complement
            }
        }

        if(!this.neighborhood.isNullOrEmpty()){
            fullAddress += "\n" + this.neighborhood

        }

        if(!this.city.isNullOrEmpty()){
            if(!this.neighborhood.isNullOrEmpty()){
                fullAddress += " - "
            }

            fullAddress += this.city
        }

        if(!this.state.isNullOrEmpty()){
            fullAddress += "-" + this.state

        }

        return fullAddress

    }

}
