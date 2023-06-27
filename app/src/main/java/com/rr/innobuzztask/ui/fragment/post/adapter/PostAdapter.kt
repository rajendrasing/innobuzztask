package com.rr.innobuzztask.ui.fragment.post.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.rr.innobuzztask.databinding.RvPostBinding
import com.rr.innobuzztask.model.response.PostResponse
import com.rr.innobuzztask.ui.fragment.post.IPostFragment


class PostAdapter(
    var postList: List<PostResponse>,
    var listener : IPostFragment
) : RecyclerView.Adapter<PostAdapter.MainView>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainView {
        val binding = RvPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainView(binding)
    }

    override fun onBindViewHolder(holder: MainView, position: Int) {

        holder.binding.post = postList[position]
        holder.binding.setVariable(BR.listener,listener)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = postList.size

    inner class MainView(var binding: RvPostBinding) : RecyclerView.ViewHolder(binding.root)

}