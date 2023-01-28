package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel

import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.githubseconds.databinding.ActivityFavoriteUserBinding
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.util.Resource
import com.dicoding.viewmodel.FavUserVm

class FavUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapterUser: AdapterUser
    private val viewModel: FavUserVm by viewModel()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterUser.notifyDataSetChanged()
        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback {
            override fun onItemClik(data: ItemResult) {
//                Intent(this@FavUserActivity, DetailUser::class.java).also {
//                    it.putExtra(DetailUser.USERNAME_GITHUB, data.login)
//                    it.putExtra(DetailUser.EXTRA_ID, data.id)
//                    it.putExtra(DetailUser.EXTRA_URL_AVATAR, data.avatarUrl)
//                    startActivity(it)
//                }
            }

        })
        binding.rcvFavUser.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavUserActivity)
            adapter = adapterUser
        }
        getFavorite()
//        viewModel.getFavoriteUser()?.observe(this) {
//            if (it != null) {
//                val list = mapList(it)
//                adapterUser.addList(list)
//            }
//        }
    }

    private fun getFavorite() {
        viewModel.favoriteUser.observe(this) {
            if (it != null) {
                when (it) {
                    is Resource.Success<*> -> {
                        it.data?.let { data -> adapterUser.addList(data as ArrayList<ItemResult>) }
                    }
                    is Resource.Loading<*> -> {


                    }
                    is Resource.Error<*> -> {
                        Toast.makeText(this, "ListUser empty list", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
//
//    private fun mapList(users: List<FavoriteUser>): ArrayList<ItemResult> {
//        val listUsers = ArrayList<ItemResult>()
//        for (user in users) {
//            val userMapped = ItemResult(
//                user.id
//
//            )
//            listUsers.add(userMapped)
//        }
//        return listUsers
//    }
}