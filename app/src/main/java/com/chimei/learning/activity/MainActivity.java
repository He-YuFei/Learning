package com.chimei.learning.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chimei.learning.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAnonymousClass;
    private Button buttonLambda;
    private Button buttonImplListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAnonymousClass = findViewById(R.id.btn_anonymous_class);
        buttonLambda = findViewById(R.id.btn_lambda);
        buttonImplListener = findViewById(R.id.btn_impl_listener);

        setTitle(R.string.title_button_click_listener);

        // 1. 通过匿名内部类设置按钮的点击事件
        buttonAnonymousClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.btn_anonymous_class, Toast.LENGTH_SHORT).show();
            }
        });

        // 2. 通过 Lambda 表达式设置按钮的点击事件
        // Lambda 补充 : Lambda 只能作用于单个方法的接口(interface)
        // v 是此方法传过去的对象名称(可以起任意名称), 多个参需要()包裹且通过 , 分割, -> 之后是业务代码, 多行需要 {} 包裹.
        buttonLambda.setOnClickListener((v -> Toast.makeText(this, R.string.btn_lambda, Toast.LENGTH_SHORT).show()));

        // 3. 通过实现接口设置按钮的点击事件, 具体看 onClick, 实现 View.OnClickListener 接口并覆写 onClick 方法
        buttonImplListener.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // 如果按钮为多个可改为 switch 语句
        if (id == R.id.btn_impl_listener) {
            Toast.makeText(MainActivity.this, R.string.btn_impl_listener, Toast.LENGTH_SHORT).show();
        }
    }
}
