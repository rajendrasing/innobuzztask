package com.rr.innobuzztask.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rr.innobuzztask.dialogs.Loading
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    abstract val layoutId: Int
    abstract val bindingVariable: Int
    abstract fun init()
    abstract fun setUpObserver()



    private lateinit var loading: Loading
    lateinit var activity: Activity
    lateinit var mViewModel: V
    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loading = Loading(this).build()

        performDataBinding()

    }

    private fun performDataBinding() {
        activity = this
        val clazz: Class<V> = getViewModelClass(javaClass)
        mViewModel = ViewModelProvider(this).get(clazz)
        binding = DataBindingUtil.setContentView(activity, layoutId)
        binding.setVariable(bindingVariable,mViewModel)
        binding.executePendingBindings()


        init()

        setUpObserver()
    }

    fun showProgress() {
        loading.showProgress()
    }

    fun showProgress(msg: String) {
        loading.showProgress(msg)
    }

    fun hideProgress() {
        loading.dismiss()
    }

    private fun getViewModelClass(aClass: Class<*>): Class<V> {
        val type = aClass.genericSuperclass

        return if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<V>
        } else {
            getViewModelClass(aClass.superclass)
        }
    }

}