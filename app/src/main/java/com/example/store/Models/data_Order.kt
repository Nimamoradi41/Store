package com.example.store.Models

import java.io.Serializable

class data_Order  :Serializable{
   var orderStatus:Int ?=null
   var price:Int ?=null
   var discountPrice:Int ?=null
   var pricePay:Int ?=null
   var discountId:Int ?=null
   var discountCode:String ?=null
   var addressFullName:String ?=null
   var addressName:String ?=null
   var fullLocation:String ?=null
   var latitude:String ?=null
   var longitude:String ?=null
   var addressTel:String ?=null
   var addressPeykInfo:String ?=null
   var addressDistance:String ?=null
   var deliveryCode:String ?=null
   var deliveryPriceForShow:String ?=null
   var numberTracking:String ?=null
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
   var discuntMessage:String ?=null
   var orderStatusTitle:String ?=null
   var deliveryTimeMessage:String ?=null
   var description:String ?=null
   var orderItems:ArrayList<orderItems> ?=null
}
