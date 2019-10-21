package com.jaydev.instagram.util.ext

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.verticalSpace(height: Int) {
	addItemDecoration(object : RecyclerView.ItemDecoration() {
		override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
			if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount!! - 1) {
				outRect.bottom = view.context.dpToPixel(height.toFloat()).toInt()
			}
		}
	})
}