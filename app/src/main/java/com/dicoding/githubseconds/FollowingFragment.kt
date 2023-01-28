package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.githubseconds.databinding.FollowFragmentBinding
import com.dicoding.util.Resource
import com.dicoding.util.TypeView
import com.dicoding.viewmodel.FollowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment : Fragment(R.layout.follow_fragment) {
    private var _binding: FollowFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel :  FollowViewModel by viewModel()
    private lateinit var adapterUser: AdapterUser
    private lateinit var username: String
    private var type: String? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FollowFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
//        username = args?.getString(DetailUser.USERNAME_GITHUB).toString()
        adapterUser.notifyDataSetChanged()

        binding.rvFollow.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
        when(type) {
            resources.getString(R.string.following) -> viewModel.setFollow(username, TypeView.FOLLOWING)
            resources.getString(R.string.followers) -> viewModel.setFollow(username, TypeView.FOLLOWER)
            else -> Toast.makeText(context, "following not data", Toast.LENGTH_SHORT).show()
        }
        getFollower()
//        viewLoading(true)
//
//        viewModel.getFollowing(username)
//        viewModel.showFollowing().observe(viewLifecycleOwner) {
//            if (it != null) {
//                adapterUser.addList(it)
//                viewLoading(false)
//            }
//        }

    }

    private fun getFollower(){
        viewModel.favoriteUsers.observe(viewLifecycleOwner){
            it.let {
                when(it){
                    is Resource.Success ->
                        if(!it.data.isNullOrEmpty()){
                            it.data.let { data -> adapterUser.addList(data as ArrayList<ItemResult>) }
                        }
                    is Resource.Error ->{
                        Toast.makeText(context, "List follower  Empty", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}