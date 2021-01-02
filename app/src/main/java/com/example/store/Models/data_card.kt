package com.example.store.Models

import java.io.Serializable

class data_card :Serializable {
    var orderStatus:Int ?=null
    var price:Int ?=null
    var discountPrice:Int ?=null
    var pricePay:Int ?=null
    var discountId:String ?=null
    var discountCode:Int ?=null
    var addressName:String ?=null
    var fullLocation:String ?=null
    var latitude:String ?=null
    var longitude:String ?=null
    var addressTel:String ?=null
    var addressFullName:String ?=null
    var deliveryCode:Int ?=null
    var moneyReturn:Boolean ?=null
    var moneyReturnDesc:String ?=null
    var refId:String ?=null
    var authority:String ?=null
    var bankCard:String ?=null
    var phone:String ?=null
    var email:String ?=null
    var datePayment:String ?=null
    var datePaymentFa:String ?=null
    var id:String ?=null
    var priceForShow:String ?=null
    var discountPriceForShow:String ?=null
    var pricePayForShow:String ?=null
    var orderStatusTitle:String ?=null
    var description:String ?=null
    var  orderItems:ArrayList<orderItems> ?=null


}