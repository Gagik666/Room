package com.example.roomsqlite.Screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.roomsqlite.Api.ApiInterface.NewService.retrofitBulder
import com.example.roomsqlite.Db.User
import com.example.roomsqlite.Db.UserDataBase
import androidx.appcompat.app.AppCompatActivity
import com.example.roomsqlite.Db.DBlist.apiList
import com.example.roomsqlite.Db.DBlist.dataList
import com.example.roomsqlite.databinding.ActivityMainBinding

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = UserDataBase.getDatabase(this)
        binding.btnShowUsers.setOnClickListener {
            val i = Intent(this, UserActivity::class.java)
            startActivity(i)
        }

        binding.btnDataBase.setOnClickListener {
            val i = Intent(this, DatabaseActivity::class.java)
            startActivity(i)
        }


        GlobalScope.launch(Dispatchers.Default) {
            if(dataList.size == 0) {
                finish()
                startActivity(getIntent())
                delay(10)
                withContext(Dispatchers.Main) {
                    if (dataList.size == 0) {
                        val retrofitData = retrofitBulder.getData()
                        retrofitData.enqueue(object : Callback<List<User>?> {
                            override fun onResponse(
                                call: Call<List<User>?>,
                                response: Response<List<User>?>
                            ) {
                                val responseBody = response.body()
                                responseBody?.forEach {
                                    apiList.add(it)
                                }
                            }

                            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                    }
                }
            }
        }






        GlobalScope.launch(Dispatchers.IO) {
            apiList.forEach {
            if (db.userDao().isNotExists(it.name)) {
                db.userDao().insertAll(it)
            }
        }
            dataList = db.userDao().getAll().toMutableList()

        }
    }
}