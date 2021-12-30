package com.opeqe.userlist.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.opeqe.userlist.R
import kotlinx.android.synthetic.main.view_toolbar.view.*

class CustomToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var view: View? = null

    var onBackButtonClickListener: View.OnClickListener? = null
        set(value) {
            field = value
            view?.backBtn?.setOnClickListener(onBackButtonClickListener)
        }

    init {
        view = inflate(context, R.layout.view_toolbar, this)

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar)
            val title = a.getString(R.styleable.CustomToolbar_ct_title)
            val showBack = a.getBoolean(R.styleable.CustomToolbar_ct_showBack, false)
            if (!title.isNullOrEmpty())
                view?.toolbarTitleTv?.text = title
            else
                view?.toolbarTitleTv?.visibility = View.GONE

            if (!showBack) {
                view?.backBtn?.visibility = View.GONE
                var params = view?.toolbarTitleTv?.layoutParams as FrameLayout.LayoutParams
                params.setMargins(46, 0, 0, 0)
            }

            a.recycle()
        }

    }


}