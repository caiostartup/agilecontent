package com.agilecontent.ui.main

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.agilecontent.R
import com.agilecontent.ScrollingActivity
import com.agilecontent.databinding.MainFragmentBinding
import com.agilecontent.manager.RepoManager
import com.agilecontent.models.RepositoryModel
import com.agilecontent.services.RestCallback
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonSearch.setOnClickListener {

            val materialDialog = MaterialDialog(context!!)
                .cancelOnTouchOutside(false)
                .customView(R.layout.layout_progress_dialog)

            materialDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            materialDialog.window?.setGravity(Gravity.CENTER)
            materialDialog.show()

            viewModel.getReposByUsername(binding.editTextUsername.text.toString(), object: RestCallback<List<RepositoryModel>?, Exception> {
                override fun onSuccess(data: List<RepositoryModel>?) {
                    data?.let {
                        RepoManager.repos = it
                        startScrollingActivity()
                    }
                    materialDialog.dismiss()
                }

                override fun onError(error: Exception) {
                        Snackbar.make(binding.root, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    materialDialog.dismiss()
                }
            })
        }


    }


    private fun startScrollingActivity() {
        val intent = Intent(context, ScrollingActivity::class.java)
        startActivity(intent)
    }

}