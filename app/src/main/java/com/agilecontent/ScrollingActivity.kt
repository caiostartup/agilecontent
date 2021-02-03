package com.agilecontent

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilecontent.databinding.ActivityScrollingBinding
import com.agilecontent.manager.RepoManager
import com.agilecontent.models.Owner
import com.agilecontent.ui.main.adapter.RepoAdapter
import com.bumptech.glide.Glide


class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private var owner: Owner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        for(repo in RepoManager.repos){
            if(repo.owner != null) {
                this.owner = repo.owner
                break
            }
        }
        owner?.let {
        supportActionBar?.title = it.login
            Glide.with(this).load(it.avatarUrl)
                .circleCrop()
                .into(binding.imageViewAvatar)
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView =
            binding.recyclerView // In xml we have given id rv_movie_list to RecyclerView
        val layoutManager = LinearLayoutManager(this) // you can use getContext() instead of "this"
        recyclerView.layoutManager = layoutManager
        val adapter = RepoAdapter(RepoManager.repos)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}