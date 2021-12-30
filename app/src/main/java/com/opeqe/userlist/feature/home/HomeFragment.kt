package com.opeqe.userlist.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.opeqe.userlist.R
import com.opeqe.userlist.common.Base
import com.opeqe.userlist.data.Result
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Base.CustomFragment(),UserListAdapter.OnUserClickListener {

    val viewModel: HomeViewModel by viewModel()
    val userListAdapter : UserListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        viewModel.progressBarLiveData.observe(requireActivity()) {
            setProgressIndicator(it)
        }

        viewModel.userListLiveData.observe(requireActivity()) {
            usersRv.layoutManager = LinearLayoutManager(requireContext())
            usersRv.adapter = userListAdapter
            userListAdapter.users = it
            userListAdapter.onUserClickListener = this
        }

    }

    override fun onUserClick(position: Int, user: Result) {
        val bundle = Bundle()
        bundle.putParcelable("DATA",user)
        navController
            .navigate(R.id.action_homeFragment_to_userDetailsFragment,bundle)

    }

}