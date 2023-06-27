package com.rr.innobuzztask.ui.fragment.postdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rr.innobuzztask.BR
import com.rr.innobuzztask.R
import com.rr.innobuzztask.base.BaseFragment
import com.rr.innobuzztask.databinding.FragmentPostBinding
import com.rr.innobuzztask.databinding.FragmentPostDeatialBinding
import com.rr.innobuzztask.model.response.PostResponse
import com.rr.innobuzztask.ui.fragment.post.PostViewModel
import com.rr.innobuzztask.ui.fragment.post.adapter.PostAdapter
import com.rr.innobuzztask.utils.SnackbarUtil


class PostDeatialFragment : BaseFragment<FragmentPostDeatialBinding, PostDetailViewModel>() {


    override val layoutId = R.layout.fragment_post_deatial
    override val bindingVariable = BR.mViewModel
    lateinit var post : PostResponse






    override fun setUpObserver() {

    }


    override fun init() {
        post = arguments?.getSerializable("post") as PostResponse

        binding.post = post

    }


}