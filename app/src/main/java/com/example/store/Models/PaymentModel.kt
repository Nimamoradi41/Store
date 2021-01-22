package com.example.store.Models

data class PaymentModel (
    var status: Int?,
    var refId: String?,
    var authority: String?,
    var email: String?,
    var phone: String?,
    var description: String?
)