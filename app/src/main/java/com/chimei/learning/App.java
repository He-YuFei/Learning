package com.chimei.learning;

import android.app.Application;

import com.chimei.learning.network.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 自定义一个 Application 对象
 * 并在 AndroidManifest.xml 中 application 标签属性增加 name 属性.
 * 此对象将比主 activity 更早加载, 可在 onCreate 中初始化部分组件.
 */
public class App extends Application {
    /**
     * 关于网络的注意事项, 高版本的 android 已经默认不允许非 https 的网络连接,
     * 如果需要访问非 https 的接口需要在 AndroidManifest.xml application 标签属性增加 allowClearUserData
     * 并设置为 true, 以允许使用明文传输.
     */
    private static Network network;

    @Override
    public void onCreate() {
        super.onCreate();
        // OkHttp 和 Retrofit 的实例化都采用建造者(Builder)模式进行.
        // 实例化日志拦截器, 并设置日志级别, 在 Logcat 中可以看到.
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        // 实例化 OkHttpClient 对象
        OkHttpClient client = new OkHttpClient.Builder()
                // 添加拦截器
                .addInterceptor(loggingInterceptor)
                // 设置超时
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        // 实例化 接口对象
        network = new Retrofit.Builder()
                // 如果不需要拦截器和超时设置, 添加 client 的步骤可跳过
                .client(client)
                // 添加 json 对象的转换工厂
                .addConverterFactory(GsonConverterFactory.create())
                // 设置基础地址
                .baseUrl("http://localhost:8080/")
                .build()
                // 创建 接口对象
                .create(Network.class);
    }

    public static Network getNetwork() {
        return network;
    }
}
