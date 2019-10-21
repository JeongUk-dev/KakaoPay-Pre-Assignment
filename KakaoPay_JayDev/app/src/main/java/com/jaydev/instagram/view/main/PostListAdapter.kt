package com.jaydev.instagram.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jaydev.instagram.data.Post
import com.jaydev.instagram.databinding.ItemPostViewBinding
import com.jaydev.instagram.mvvm.Event

class PostListAdapter(private val itemClickEvent: MutableLiveData<Event<Post>>) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

	private var postList = listOf<Post>()

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val post = postList[position]
		holder.apply {
			bind(createOnClickListener(post), post)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(ItemPostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun getItemCount() = postList.size

	private fun createOnClickListener(post: Post): View.OnClickListener {
		return View.OnClickListener {
			itemClickEvent.value = Event(post)
		}
	}

	fun updatePosts(newPosts: List<Post>) {
		val diffResult = DiffUtil.calculateDiff(DiffCallback(postList, newPosts) { it.id })
		this.postList = newPosts
		diffResult.dispatchUpdatesTo(this)
	}

	class ViewHolder(private val itemBinding: ItemPostViewBinding) : RecyclerView.ViewHolder(itemBinding.root) {
		fun bind(listener: View.OnClickListener, item: Post) {
			itemBinding.apply {
				clickListener = listener
				thumbnailUrl = item.images?.thumbnail?.url
				executePendingBindings()
			}
		}
	}
}

private class DiffCallback<T>(
	private val oldItems: List<T>, private val newItems: List<T>,
	private val itemIdGetter: (T) -> Any?
) : DiffUtil.Callback() {

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		itemIdGetter(oldItems[oldItemPosition]) == itemIdGetter(newItems[newItemPosition])


	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		oldItems[oldItemPosition] == newItems[newItemPosition]

	override fun getOldListSize() = oldItems.size

	override fun getNewListSize() = newItems.size
}