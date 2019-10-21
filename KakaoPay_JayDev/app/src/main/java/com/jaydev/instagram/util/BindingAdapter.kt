package com.jaydev.instagram.util

import android.graphics.Rect
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jaydev.instagram.R
import com.jaydev.instagram.data.Caption
import com.jaydev.instagram.data.Images
import com.jaydev.instagram.util.ext.dpToPixel

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean?) {
	view.visibility = when (isGone) {
		true -> View.GONE
		false -> View.VISIBLE
		else -> view.visibility
	}
}


@BindingAdapter("isInvisible")
fun bindIsInvisible(view: View, isInvisible: Boolean?) {
	view.visibility = when (isInvisible) {
		true -> View.INVISIBLE
		false -> View.VISIBLE
		else -> view.visibility
	}
}

@BindingAdapter("imageLoad")
fun bindImageLoad(view: ImageView, url: String?) {
	Glide.with(view).load(url).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(view)
}

@BindingAdapter("postImage")
fun bindPostImage(view: ImageView, image: Images?) {
	Glide.with(view).load(image?.standardResolution?.url).placeholder(R.drawable.icon_loop).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).centerCrop()).into(view)
	val constraintSet = ConstraintSet()
	constraintSet.clone(view.parent as ConstraintLayout)
	constraintSet.setDimensionRatio(view.id, "${image?.lowResolution?.width}:${image?.lowResolution?.height}")
	constraintSet.applyTo(view.parent as ConstraintLayout)
}

@BindingAdapter("caption")
fun bindCaption(textView: TextView, caption: Caption?) {
	if (caption != null) {
		val username = caption.from?.username
		val captionBuilder = SpannableStringBuilder().apply {
			append(username).append(" ").append(caption.text)
				.setSpan(StyleSpan(Typeface.BOLD), 0, username?.length ?: 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		}

		textView.text = captionBuilder
	} else {
		textView.visibility = View.GONE
	}
}

@BindingAdapter("verticalSpace")
fun bindVerticalSpace(recyclerView: RecyclerView, height: Int) {
	recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
		override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
			if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount!! - 1) {
				outRect.bottom = view.context.dpToPixel(height.toFloat()).toInt()
			}
		}
	})
}