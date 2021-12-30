package com.opeqe.userlist.services

import com.opeqe.userlist.view.CustomImageView

interface ImageLoadingService {

    fun load(imageView : CustomImageView, imageUrl : String)

}