package com.example.roomsqlite.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsqlite.Db.User
import com.example.roomsqlite.R
import com.example.roomsqlite.databinding.ItemUsersBinding
import com.squareup.picasso.Picasso

class UserAdapter (
    private val userList: MutableList<User>
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemUsersBinding.bind(itemView)
        fun bind(user: User) = with(binding) {
            tvName.text = user.name
            tvRealName.text = user.realname
            tvTeam.text = user.team
            Picasso.get().load(user.imageurl).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

}