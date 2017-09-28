package com.vacareanu.robert.trendinggithub.ui.repositories

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Created by robert on 9/27/17.
 */
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
//        itemClickCallback.onItemClick(childView, rv.getChildAdapterPosition(childView))

        if (childView != null && gestureDetector.onTouchEvent(e)) {
            itemClickCallback.onItemClick(childView, rv.getChildAdapterPosition(childView))
            return true
        }

        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    interface ItemClickCallback {
        fun onItemClick(clickedView: View, position: Int)
        //fun onLongItemClick(view: View, position: Int)
    }

}