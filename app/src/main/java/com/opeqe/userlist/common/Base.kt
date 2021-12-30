package com.opeqe.userlist.common

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import com.opeqe.userlist.App
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.IllegalStateException
import android.widget.TextView
import com.opeqe.userlist.R


class Base {

    abstract class CustomFragment : Fragment(), CustomView {

        lateinit var navController: NavController

        override val rootView: CoordinatorLayout?
            get() = view as CoordinatorLayout
        override val viewContext: Context?
            get() = context

        override fun onStart() {
            super.onStart()
            EventBus.getDefault().register(this)
        }

        override fun onStop() {
            super.onStop()
            EventBus.getDefault().unregister(this)
        }

    }

    abstract class CustomActivity : AppCompatActivity(), CustomView {

        override val rootView: CoordinatorLayout?
            get() {
                val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
                if (viewGroup !is CoordinatorLayout) {
                    viewGroup.children.forEach {
                        if (it is CoordinatorLayout)
                            return it
                    }
                    throw IllegalStateException("RootView must be instance of CoordinatorLayout")

                } else {
                    return viewGroup
                }
            }
        override val viewContext: Context?
            get() = this

        var w: Window? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            EventBus.getDefault().register(this)
            w = window


        }

        fun removeNavAndStatusBar(){
            w!!.addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        override fun onDestroy() {
            super.onDestroy()
            EventBus.getDefault().unregister(this)
        }

    }

    interface CustomView {

        val rootView: CoordinatorLayout?
        val viewContext: Context?
        fun setProgressIndicator(mustShow: Boolean) {

            rootView?.let {
                var loadingView = it.findViewById<View>(R.id.loadingView)
                viewContext?.let { context ->
                    if (loadingView == null && mustShow) {
                        loadingView =
                            LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
                        it.addView(loadingView)
                    }
                    loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
                }

            }

        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        fun showError(customException: CustomException) {
            viewContext?.let {
                when (customException.type) {
                    CustomException.Type.SIMPLE -> {
                        showSnackBar(
                            customException.serverMessage
                                ?: it.getString(customException.userFriendlyMessage)
                        )
                    }
                    CustomException.Type.AUTH -> {

                    }
                }
            }
        }

        fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
            rootView?.let {
                val snackBar = Snackbar.make(it, message, duration)
                snackBar.show()
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(App.instance!!.resources.getColor(R.color.white))
                val textMessage = snackBarView.findViewById<View>(R.id.snackbar_text) as TextView
                textMessage.setTextColor(App.instance!!.resources.getColor(R.color.black))

            }
        }
    }

    abstract class CustomViewModel : ViewModel() {

        val progressBarLiveData = MutableLiveData<Boolean>()
        val compositeDisposable = CompositeDisposable()

        override fun onCleared() {
            compositeDisposable.clear()
            super.onCleared()
        }

    }

}