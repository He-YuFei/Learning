package com.chimei.learning.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chimei.learning.AppKotlin
import com.chimei.learning.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class RetrofitKotlinActivity : AppCompatActivity(), Callback<String> {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_retrofit)

        btn_anonymous_class.setOnClickListener {
            AppKotlin.getNetwork().sample().enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@RetrofitKotlinActivity, R.string.message_fail, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RetrofitKotlinActivity, response.body(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        btn_sync.setOnClickListener {
            Toast.makeText(this, syncGetResult(), Toast.LENGTH_SHORT).show()
        }

        btn_impl_listener.setOnClickListener {
            AppKotlin.getNetwork().sample().enqueue(this)
        }
    }

    private fun syncGetResult(): String? {
        val task: FutureTask<String> = FutureTask(object : Callable<String> {
            override fun call(): String? {
                val response: Response<String> = AppKotlin.getNetwork().sample().execute()
                if (response.isSuccessful) {
                    return response.body()
                }
                return null
            }
        })
        return try {
            task.get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        Toast.makeText(this@RetrofitKotlinActivity, R.string.message_fail, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            Toast.makeText(this@RetrofitKotlinActivity, response.body(), Toast.LENGTH_SHORT).show()
        }
    }
}