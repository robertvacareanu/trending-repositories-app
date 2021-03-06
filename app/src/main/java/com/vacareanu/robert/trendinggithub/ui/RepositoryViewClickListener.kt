package com.vacareanu.robert.trendinggithub.ui.repositories

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class RepositoryViewClickListener(private val itemClickCallback: ItemClickCallback, context: Context) : RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector

    init {
        this.gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }
        })
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {}

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {

        val childView = rv!!.findChildViewUnder(e!!.x, e.y)

        if (childView != null && gestureDetector.onTouchEvent(e)) {

            val imageView = findChildOf<ImageView>(childView, e.x, e.y)


            imageView?.let {
                itemClickCallback.onHeartClick(imageView, rv.getChildAdapterPosition(childView))
                return true
            }

            itemClickCallback.onItemClick(rv.getChildAdapterPosition(childView))

            return true
        }

        return false
    }

    private inline fun <reified T : View> findChildOf(childView: View, x: Float, y: Float): T? = findChildrenUnder(childView, x, y, childView.x, childView.y).firstOrNull { it is T } as T?

    private fun findChildrenUnder(childView: View, x: Float, y: Float, xOffset: Float, yOffset: Float): List<View> {
        val result = mutableListOf(childView)

        if (childView is ViewGroup) {
            val childCount = childView.childCount

            (0 until childCount)
                    .map { childView.getChildAt(it) }
                    .filter { x - xOffset >= it.left + it.translationX && x - xOffset <= it.right + it.translationX && y - yOffset <= it.bottom + it.translationY && y - yOffset >= it.top + it.translationY }
                    .forEach { if (it is ViewGroup) result.addAll(findChildrenUnder(it, x, y, childView.x, childView.y)) else result.add(it) }

        }
        return result

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}


    interface ItemClickCallback {
        fun onHeartClick(heart: ImageView, position: Int)
        fun onItemClick(position: Int)
        //fun onLongItemClick(view: View, position: Int)
    }

}