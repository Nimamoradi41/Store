package com.example.store.Models

import java.io.Serializable

class orderItems :Serializable {
var  id:String? =null
var  productId:String? =null
var  productTitle:String? =null
var  count:Int? =null
var  productFirstImage:String? =null
var  barcode:String? =null
var  price:Int? =null
var  maxCountReserve:Int? =null
var  pricePay:Int? =null
var  priceForShow:String? =null
var  pricePayForShow:String? =null
}
