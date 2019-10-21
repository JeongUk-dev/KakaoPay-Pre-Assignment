package com.jaydev.instagram.view.post

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jaydev.instagram.R
import com.jaydev.instagram.base.BaseActivity
import com.jaydev.instagram.data.Post
import com.jaydev.instagram.databinding.ActivityPostBinding

class PostActivity : BaseActivity<ActivityPostBinding>(R.layout.activity_post) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.post = intent.getSerializableExtra(INTENT_POST) as Post
	}

	companion object {
		val INTENT_POST = "INTENT_POST"
		fun start(context: Context, post: Post) = Intent(context, PostActivity::class.java).apply {
			putExtra(INTENT_POST, post)
		}
	}
}