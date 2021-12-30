package com.opeqe.userlist.services

import com.facebook.drawee.view.SimpleDraweeView
import com.opeqe.userlist.R
import com.opeqe.userlist.view.CustomImageView
import java.lang.IllegalStateException

class FrescoImageLoadingService : ImageLoadingService {

    override fun load(imageView: CustomImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView) {
            imageView.setImageURI(imageUrl)
            imageView.hierarchy.setPlaceholderImage(R.drawable.ic_user_place_holder)
        } else {
            throw IllegalStateException("ImageView must be instance of simpleDraweeView")
        }
    }

}