package com.jaydev.instagram.view.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jaydev.instagram.R
import com.jaydev.instagram.base.BaseActivity
import com.jaydev.instagram.databinding.ActivityMainBinding
import com.jaydev.instagram.mvvm.EventObserver
import com.jaydev.instagram.util.ext.obtainViewModel
import com.jaydev.instagram.view.post.PostActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

	val postSpanCount = 3

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.viewModel = obtainViewModel(MainViewModel::class.java).also {
			val adapter = setupPostList(binding, it)
			subscribeUI(adapter, it)
		}


		binding.viewModel!!.getMyInfo()
		binding.viewModel!!.getMyPostList()
	}

	private fun setupPostList(binding: ActivityMainBinding, viewModel: MainViewModel): PostListAdapter {
		val adapter = PostListAdapter(viewModel.postClickEvent)
		binding.postRecyclerView.run {
			this.adapter = adapter
			layoutManager = GridLayoutManager(this@MainActivity, postSpanCount)
		}
		return adapter
	}

	private fun subscribeUI(adapter: PostListAdapter, viewModel: MainViewModel) {
		viewModel.run {
			postList.observe(this@MainActivity, Observer { postList ->
				adapter.updatePosts(postList)
			})

			postClickEvent.observe(this@MainActivity, EventObserver { post ->
				startActivity(PostActivity.start(this@MainActivity, post))
			})

			netErrorDialog.observe(this@MainActivity, EventObserver { errorMessage ->
				AlertDialog.Builder(this@MainActivity).setMessage(getString(R.string.network_fail, errorMessage))
					.setPositiveButton(android.R.string.ok, null).show()
			})
		}
	}
}
