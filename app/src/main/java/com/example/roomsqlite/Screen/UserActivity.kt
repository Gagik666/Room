package com.example.roomsqlite.Screen

import android.os.Bundle
import com.example.roomsqlite.Adapter.UserAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsqlite.Db.DBlist.dataList
import com.example.roomsqlite.R
import com.example.roomsqlite.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {

    lateinit var adapter: UserAdapter
    lateinit var bindinfg: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinfg = ActivityUserBinding.inflate(layoutInflater)
        setContentView(bindinfg.root)

            adapter = UserAdapter(dataList)
            bindinfg.rvUsers.adapter = adapter

        }

}