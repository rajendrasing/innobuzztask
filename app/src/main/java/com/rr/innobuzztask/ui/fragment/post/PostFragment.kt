package com.rr.innobuzztask.ui.fragment.post

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.rr.innobuzztask.BR
import com.rr.innobuzztask.R
import com.rr.innobuzztask.base.BaseFragment
import com.rr.innobuzztask.databinding.FragmentPostBinding
import com.rr.innobuzztask.model.response.PostResponse
import com.rr.innobuzztask.ui.fragment.post.adapter.PostAdapter
import com.rr.innobuzztask.utils.SnackbarUtil


class PostFragment : BaseFragment<FragmentPostBinding, PostViewModel>(),IPostFragment {


    override val layoutId = R.layout.fragment_post
    override val bindingVariable = BR.mViewModel

    lateinit var adapter : PostAdapter




    override fun setUpObserver() {
        mViewModel.post.observe(this) {
            it?.let {
                adapter = PostAdapter(it,this)
                binding.rv.adapter = adapter
            }
        }

        mViewModel.errorMessage.observe(this) {
            SnackbarUtil.show(activity, it)
        }

        mViewModel.showProgress.observe(this) {
            if (it) showProgress()
            else hideProgress()
        }
    }


    override fun init() {
    }

    override fun onPostClick(post : PostResponse) {
        var bundel = Bundle()
        bundel.putSerializable("post",post)
        findNavController().navigate(R.id.postDeatialFragment,bundel)
    }


}