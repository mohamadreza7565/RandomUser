package com.opeqe.userlist.feature.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opeqe.userlist.R
import com.opeqe.userlist.common.Base
import com.opeqe.userlist.data.Result
import com.opeqe.userlist.services.ImageLoadingService
import kotlinx.android.synthetic.main.fragment_user_details.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class UserDetailsFragment : Base.CustomFragment() {

    private lateinit var user : Result
    private val imageLoadingService : ImageLoadingService by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       user = arguments?.getParcelable("DATA")!!

        imageLoadingService.load(profileIv,user.picture.large)
        nameTv.text = "${user.name.title} ${user.name.first} ${user.name.last}"
        genderTv.text = user.gender
        locationTv.text = "${user.location.country} - ${user.location.state} - ${user.location.city}"
        emailTv.text = user.email
        phoneTv.text = user.phone
        cellTv.text = user.cell

        toolbarView.onBackButtonClickListener = View.OnClickListener {
            requireActivity().onBackPressed()
        }

    }

}