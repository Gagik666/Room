package com.example.roomsqlite.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomsqlite.Adapter.DatabaseAdapter
import com.example.roomsqlite.Db.DBlist.dataList
import com.example.roomsqlite.R
import com.example.roomsqlite.databinding.ActivityDatabaseBinding

class DatabaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityDatabaseBinding
    lateinit var dataAdapter: DatabaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataAdapter = DatabaseAdapter(dataList)
        binding.rvDatabase.adapter = dataAdapter
    }
}