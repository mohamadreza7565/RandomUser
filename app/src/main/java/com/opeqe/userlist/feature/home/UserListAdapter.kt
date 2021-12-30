package com.opeqe.userlist.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opeqe.userlist.R
import com.opeqe.userlist.common.implementSpringAnimationTrait
import com.opeqe.userlist.data.Result
import com.opeqe.userlist.services.ImageLoadingService
import kotlinx.android.synthetic.main.layout_user_list_item.view.*

class UserListAdapter(val context: Context, val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    var onUserClickListener: OnUserClickListener? = null

    var users: MutableList<Result> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(user: Result) {
            imageLoadingService.load(itemView.profileIv, user.picture.large)
            itemView.nameTv.text = "${user.name.title} ${user.name.first} ${user.name.last}"
            itemView.genderTv.text =
                "${context.resources.getString(R.string.gender)} : ${user.gender}"
            itemView.locationTv.text =
                "${user.location.country}-${user.location.state}-${user.location.city}"
            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {
                if (onUserClickListener!=null)
                    onUserClickListener?.onUserClick(adapterPosition,user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_user_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    interface OnUserClickListener {
        fun onUserClick(position: Int, user: Result)
    }

}