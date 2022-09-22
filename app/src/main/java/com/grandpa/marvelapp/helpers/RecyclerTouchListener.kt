package com.grandpa.marvelapp.helpers

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class RecyclerTouchListener(
    context: Context?,
    recyclerView: RecyclerView,
    private var clickListener: ClickListener?
) {

    private var gestureDetector: GestureDetector? = null


    init {
        gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child: View? = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null && clickListener != null) {
                    clickListener!!.onLongClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }

    fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child: View? = rv.findChildViewUnder(e.x, e.y)
        if (child != null && clickListener != null && gestureDetector!!.onTouchEvent(e)) {
            clickListener!!.onClick(child, rv.getChildAdapterPosition(child))
        }
        return false
    }

    fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
    }

    fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    interface ClickListener {
        fun onClick(view: View?, position: Int)
        fun onLongClick(view: View?, position: Int)
    }
}