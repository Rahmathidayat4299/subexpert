package com.dicoding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.githubseconds.databinding.ItemListuserBinding
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.util.Resource

class AdapterUser(private val user: ArrayList<ItemResult>) : RecyclerView.Adapter<AdapterUser.ListViewHolder>() {
//    private var listUser = ArrayList<ItemResult>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

//    @SuppressLint("NotifyDataSetChanged")
    fun addList(item: List<ItemResult>?) {
        user.apply {
            clear()
            item?.let { addAll(it) }

        }
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemListuserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: ItemResult) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClik(users)
            }
            binding.apply {
                name.text = users.login
                username.text = users.id.toString()
            }
            Glide.with(itemView.context)
                .load(users.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageUser)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemListuserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(user[position])
    }

    override fun getItemCount() = user.size

    interface OnItemClickCallback {
        fun onItemClik(data: ItemResult)
    }
}
