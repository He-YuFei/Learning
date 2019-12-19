package com.chimei.learning.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chimei.learning.R
import kotlinx.android.synthetic.main.activity_main.*

class MainKotlinActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        btn_lambda.setOnClickListener{
            Toast.makeText(this, R.string.btn_lambda, Toast.LENGTH_SHORT).show()
        }

        btn_anonymous_class.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@MainKotlinActivity, R.string.btn_anonymous_class, Toast.LENGTH_SHORT).show()
            }
        })

        btn_impl_listener.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_impl_listener) {
            Toast.makeText(this, R.string.btn_impl_listener, Toast.LENGTH_SHORT).show()
        }
    }
}