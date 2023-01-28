package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.darkmode.SettingApp
import com.dicoding.githubseconds.databinding.ActivityListUserBinding
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.util.Resource
import com.dicoding.viewmodel.ListUserVm


class ListUser : AppCompatActivity() {
    private lateinit var binding: ActivityListUserBinding
    private val viewModel : ListUserVm by viewModel()
    private lateinit var adapterUser: AdapterUser

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapterUser = AdapterUser(arrayListOf())
        adapterUser.notifyDataSetChanged()

        binding.rcvUser.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(this@ListUser, LinearLayoutManager.VERTICAL, false)
        }
        binding.search.apply {

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.isNotEmpty()!!
                    ) {
                        viewLoading(true)
                        viewModel.searchUser(query)
                        showData()
                    }
                    return true
                }


                override fun onQueryTextChange(newText: String?): Boolean = false
            })
            getListUser()

        }

        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback {
            override fun onItemClik(data: ItemResult) {
//                Intent(this@ListUser, DetailUser::class.java).also {
//                    it.putExtra(DetailUser.USERNAME_GITHUB, data.login)
//                    it.putExtra(DetailUser.EXTRA_ID, data.id)
//                    it.putExtra(DetailUser.EXTRA_URL_AVATAR, data.avatarUrl)
//                    it.putExtra(DetailUser.EXTRA_URL, data.url)
//                    startActivity(it)
//                }
            }
        })


    }

    private fun getListUser() {
        viewModel.user.observe(this) {
            if (it != null) {
                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { data -> adapterUser.addList(data) }
//                        showData()
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE

                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "ListUser empty list", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                Intent(this, FavUserActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.setting_menu -> {
                Intent(this, SettingApp::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun viewLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showData() {
        binding.rcvUser.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(this@ListUser, LinearLayoutManager.VERTICAL, false)
        }
    }

}