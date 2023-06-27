package com.rr.innobuzztask.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rr.innobuzztask.dialogs.Loading
import com.rr.innobuzztask.utils.SnackbarUtil
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewDataBinding,V : ViewModel> : Fragment() {

    abstract val layoutId: Int
    abstract val bindingVariable: Int

    abstract fun setUpObserver()

    abstract fun init()

    lateinit var mViewModel: V
    lateinit var binding: T

    private lateinit var loading: Loading
    lateinit var mContext: Context
    val TAG = javaClass.name

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        val view = binding.root
        loading = Loading(mContext).build()

        performDataBinding()


        // Inflate the layout for this fragment
        return view
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


    private fun performDataBinding() {
        val clazz: Class<V> = getViewModelClass(javaClass)
        mViewModel = ViewModelProvider(this).get(clazz)
        binding.setVariable(bindingVariable, mViewModel)
        binding.executePendingBindings()
        init()
        setUpObserver()
    }

    fun showSnackBar(msg: String) {
        SnackbarUtil.show(requireActivity(),msg)
    }

    fun delay(delay: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, delay)
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