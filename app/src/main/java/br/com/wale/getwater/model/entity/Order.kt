package br.com.wale.getwater.model.entity

/**
 * Created by Admin on 28/07/17.
 */

data class Order(val onlineId: Long,
                 val orderNumber: String,
                 val totalPrice: String,
                 val paymentType: Int,
                 val cupom: String?,
                 val userId: Long,
                 val orderStatus: Int?,
                 val paymentStatus: Int?)