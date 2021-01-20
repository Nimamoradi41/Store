package com.example.store;

import com.example.store.Main_Fragments.Edit_Profile;
import com.example.store.Main_Fragments.Editprofile_Bank;
import com.example.store.Models.Defult_Response;
import com.example.store.Models.GetProductModel;
import com.example.store.Models.RESPONSADRESS;
import com.example.store.Models.RESPONSCARD;
import com.example.store.Models.ResPonseProfile;
import com.example.store.Models.ResponDelAddress;
import com.example.store.Models.ResponseAddress;
import com.example.store.Models.ResponseGetProduct;
import com.example.store.Models.ResponseMoreData;
import com.example.store.Models.ResponseOrder;
import com.example.store.Models.ResponseSEARCH;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @Multipart
    @POST("/api/Account/ConfirmSms")
    Call<ConfirmSmsResponse> confirmSms(@Part("body") RequestBody body);


    @Multipart
    @POST("/api/Account/Login")
    Call<LoginResponse> login(@Part("body") RequestBody body);




    @POST("/api/Home/GetHome")
    Call<RESPOSNHOME> GETHOME(@Header("Authorization") String token);



    @POST("/api/order/GetOrder")
    Call<ResponseOrder> GetOrder(@Header("Authorization") String token);

    @POST("/api/Profile/GetProfile")
    Call<ResPonseProfile> GetProfile(@Header("Authorization") String token);



    @Multipart
    @POST("/api/Profile/EditProfile")
    Call<ResPonseProfile> EditProfile(@Header("Authorization") String token, @Part("body") Edit_Profile edit_profile);


    @Multipart
    @POST("/api/Profile/EditBankinfo")
    Call<Defult_Response> EditBankinfo(@Header("Authorization") String token, @Part("body") Editprofile_Bank edit_profile);


    @POST("/api/Address/GetAll")
    Call<RESPONSADRESS> GETADDRESS(@Header("Authorization") String token);

    @Multipart
    @POST("/api/order/GetCart")
    Call<RESPONSCARD> GETCARD(@Header("Authorization") String token,@Part("body") RequestBody body);



    @Multipart
    @POST("/api/Address/AddEdit")
    Call<ResponseAddress> AddEditAddress(@Header("Authorization") String token, @Part("body") ModelAddress body);


    @Multipart
    @POST("/api/Address/Delete")
    Call<ResponDelAddress> DeleteAddress(@Header("Authorization") String token, @Part("body") RequestBody body);


    @Multipart
    @POST("/api/Product/GetProduct")
    Call<ResponseGetProduct> GetProduct(@Header("Authorization") String token, @Part("body") GetProductModel model,
                                        @Part("order") RequestBody body);



    @Multipart
    @POST("/api/Product/GetFilter")
    Call<ResponseSEARCH> GetFilter(@Header("Authorization") String token, @Part("body") GetProductModel model);



    @Multipart
    @POST("/api/order/AddEditDeleteItem")
    Call<Respons_AddEditDeleteItem_Card> AddEditDeleteItem_Card(@Header("Authorization") String token,@Part("body") AddEditDeleteItemModel model);



    @Multipart
    @POST("/api/Product/GetProduct")
    Call<ResponseMoreData>
    GetMoreData(@Header("Authorization")
                                   String token,@Part("body") GetProductModel model,@Part("page") RequestBody body,
                @Part("order") RequestBody order);


    @Multipart
    @POST("/api/CategoryAndBrand/Search")
    Call<ResponseSEARCH>
    Search(@Header("Authorization")
                        String token,@Part("body") RequestBody body);

}
