package com.example.roomsqlite.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsqlite.Db.User
import com.example.roomsqlite.R
import com.example.roomsqlite.databinding.ItemDatabaseBinding
import com.squareup.picasso.Picasso

class DatabaseAdapter(
    private val datalist: List<User>
): RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder>() {
    class DatabaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) = with(binding) {
            tvDataName.text = user.name
            tvDataRealName.text = user.realname
            tvDataTeam.text = user.team
            tvDataImageUrl.text = user.imageurl
            Picasso.get().load(user.imageurl).into(imgDatabase)

            tvDataImageUrl.setOnClickListener {
                imgDatabase.visibility = View.VISIBLE
                tvDataImageUrl.visibility = View.INVISIBLE
            }

            imgDatabase.setOnClickListener {
                imgDatabase.visibility = View.GONE
                tvDataImageUrl.visibility = View.VISIBLE
            }
        }

        val binding = ItemDatabaseBinding.bind(itemView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DatabaseViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_database, parent, false)
    )

    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    override fun getItemCount() = datalist.size
}