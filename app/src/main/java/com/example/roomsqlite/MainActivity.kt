package com.example.roomsqlite

import android.os.Bundle
import com.example.roomsqlite.Adapter.UserAdapter
import com.example.roomsqlite.Api.ApiInterface.NewService.retrofitBulder
import com.example.roomsqlite.Db.User
import com.example.roomsqlite.Db.UserDataBase
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsqlite.Db.DBlist.dataList
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserAdapter
    lateinit var newsList: RecyclerView


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = UserDataBase.getDatabase(this)
        newsList = findViewById(R.id.rvUsers)

        GlobalScope.launch(Dispatchers.IO) {
            val retrofitData = retrofitBulder.getData()
            retrofitData.enqueue(object : Callback<List<User>?> {
                override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                    val responseBody = response.body()
                    responseBody?.forEach {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.userDao().insertAll(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

        CoroutineScope(Dispatchers.IO).launch {
            dataList += (db.userDao().getAll()).toMutableList()
        }

        adapter = UserAdapter(dataList)
        newsList.adapter = adapter
    }


    fun check(list1: MutableList<User>): Boolean {
        for (i in 0 until list1.size)
            for (j in 0 until list1.size)
                if (i!=j && list1[i] == list1[j])
                    return true
        return false
    }
}