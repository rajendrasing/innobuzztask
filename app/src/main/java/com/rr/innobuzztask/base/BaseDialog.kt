package com.rr.innobuzztask.base

import android.app.Dialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rr.innobuzztask.dialogs.Loading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseDialog<T : ViewDataBinding>(mContext: Context):   Dialog(mContext),
    CoroutineScope {

    private var _binding : T? = null

    val binding : T get() = _binding!!

    abstract var layoutId: Int
    abstract var className: Dialog
    private lateinit var loading: Loading
    val TAG = javaClass.name
    override val coroutineContext = Dispatchers.IO

    open fun build(): Dialog {
         performDataBinding()
         loading = Loading(this.context).build()
        return className
    }

    private fun performDataBinding() {

       _binding = DataBindingUtil.inflate(
            layoutInflater,
            layoutId,
            null,
            false
        )
        binding.root.let { this.setContentView(it) }
    }

    open fun showProgress() {
        loading.showProgress()
    }

    open fun showProgress(msg: String) {
        loading.showProgress(msg)
    }

    open fun hideProgress() {
        loading.dismiss()
    }
  
}