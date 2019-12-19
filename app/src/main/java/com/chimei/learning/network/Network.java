package com.chimei.learning.network;

import com.chimei.learning.entity.LoginParam;
import com.chimei.learning.entity.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Call 是一个回调对象, <> 内为服务器返回数据的类型
 */
public interface Network {

    @GET("sample")
    Call<String> sample();

    /**
     * 返回服务器的原始数据对象
     * @return
     */
    @GET("download/update.apk")
    Call<ResponseBody> downloadUpdate();

    /**
     * Get 请求
     * @param age 参数
     * @param address 参数
     * @return
     */
    @GET("list")
    Call<List<String>> getNames(@Query("age") int age, @Query("address") String address);

    /**
     * Post 请求
     * @param username
     * @param password
     * @return
     */
    @POST("login")
    Call<User> login(@Field("username") String username, @Field("password") String password);

    /**
     * 通过传输 json 的方式登录
     * @param loginParam
     * @return
     */
    @POST("login")
    Call<User> loginWithJson(@Body LoginParam loginParam);
}
