package com.example.store.Models

class GetProductModel {
    var type:Int ?=null
//    var CategoryId:String ?=null
    var CategoryIds:ArrayList<String> ?=null
//    var brandId:String ?=null
    var brandIds:ArrayList<String> ?=null
    var isDiscount:Boolean ?=null
    var discountPercent:Int ?=null
    var productTitle:String ?=null
}