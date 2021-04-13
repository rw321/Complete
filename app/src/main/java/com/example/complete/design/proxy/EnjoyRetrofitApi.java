package com.example.complete.design.proxy;

import okhttp3.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.POST;


public interface EnjoyRetrofitApi {


    @POST("shopping_cart/cart_add")
    Call addShoppingCar(@Field("sku_id") String skuId , @Field("num") String num , @Field("source") String source);


}
