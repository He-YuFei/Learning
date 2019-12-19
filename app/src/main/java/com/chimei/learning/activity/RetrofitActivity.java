package com.chimei.learning.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chimei.learning.App;
import com.chimei.learning.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 这里主要是 Retrofit 的使用
 * 除同步的方式外, 其它都是异步的方式获取数据
 * 按钮的点击事件默认采用 Lambda 的方式设置, 请查看 -> 之后的代码
 *
 * 实现 Callback<> 方法, <> 为返回数据的类型
 *
 */
public class RetrofitActivity extends AppCompatActivity implements Callback<String> {
    private Button buttonAnonymousClass;
    private Button buttonSync;
    private Button buttonImplInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        buttonAnonymousClass = findViewById(R.id.btn_anonymous_class);
        buttonSync = findViewById(R.id.btn_sync);
        buttonImplInterface = findViewById(R.id.btn_impl_listener);

        buttonAnonymousClass.setOnClickListener(v -> {
            // 1. 匿名内部类
            App.getNetwork().sample().enqueue(new Callback<String>() {
                // 获取成功的处理
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RetrofitActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }
                }
                // 获取失败的处理
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(RetrofitActivity.this, R.string.message_fail, Toast.LENGTH_SHORT).show();
                }
            });
        });


        buttonSync.setOnClickListener(v -> {
            // 2. 同步获取
            String result = syncGetResult();
            if (result != null) {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.message_fail, Toast.LENGTH_SHORT).show();
            }
        });


        buttonImplInterface.setOnClickListener(v ->
            // 3. 通过实现接口设置按钮的点击事件
                App.getNetwork().sample().enqueue(this));
    }

    /**
     * 同步获取数据
     * 不推荐, 网络不好或数据量大的情况下同步会导致程序卡住(线程阻塞)
     * @return String
     */
    private String syncGetResult() {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Response<String> response = App.getNetwork().sample().execute();
                if (response.isSuccessful()) {
                    return response.body();
                }
                return null;
            }
        });
        try {
            return task.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 覆写的成功处理方法
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            Toast.makeText(RetrofitActivity.this, response.body(), Toast.LENGTH_SHORT).show();
        }
    }
    // 覆写的失败处理方法
    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Toast.makeText(this, R.string.message_fail, Toast.LENGTH_SHORT).show();
    }
}
