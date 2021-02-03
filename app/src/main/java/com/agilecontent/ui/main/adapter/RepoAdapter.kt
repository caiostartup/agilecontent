package com.agilecontent.ui.main.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agilecontent.databinding.ItemRepoBinding
import com.agilecontent.models.RepositoryModel


class RepoAdapter(val repos: List<RepositoryModel>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemRepoBinding =
            ItemRepoBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return repos.count()
    }

    class RepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * We will use this function to bind instance of Movie to the row
         */
        fun bind(repo: RepositoryModel?) {
            binding.repo = repo
            binding.executePendingBindings()
        }
    }

}