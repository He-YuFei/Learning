package com.chimei.learning.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chimei.learning.R

class MainKotlinActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var buttonLambda: Button
    private lateinit var buttonAnonymousClass: Button
    private lateinit var buttonImplListener: Button

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        buttonLambda = findViewById(R.id.btn_lambda)
        buttonLambda.setOnClickListener{
            Toast.makeText(this, R.string.btn_lambda, Toast.LENGTH_SHORT).show()
        }

        buttonAnonymousClass = findViewById(R.id.btn_anonymous_class)
        buttonAnonymousClass.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@MainKotlinActivity, R.string.btn_anonymous_class, Toast.LENGTH_SHORT).show()
            }
        })

        buttonImplListener = findViewById(R.id.btn_impl_listener)
        buttonImplListener.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_impl_listener) {
            Toast.makeText(this, R.string.btn_impl_listener, Toast.LENGTH_SHORT).show()
        }
    }
}