package com.cobee.android.sonsu

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration (
    //그릴 divider의 높이와 색상을 받는다
    private val dividerHeight: Int,
    private val dividerColor: Int = Color.TRANSPARENT
    ) : RecyclerView.ItemDecoration()
    {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val position = parent.getChildAdapterPosition(view)
            val count = state.itemCount
            val offset = 20

            if(position == 0) {
                outRect.top = offset
            } else if(position == count - 1){
                outRect.bottom = offset
            } else {
                outRect.top = offset
                outRect.bottom = offset
            }
        }

        //c.drawRect 에서 사용될 변수 선언
        private val paint = Paint()

        // recyclerView 보다 먼저 그려지는 함수
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            myDivider(c, parent, color = dividerColor)
        }

        private fun myDivider(c: Canvas, parent: RecyclerView, color: Int) {
            paint.color = color

            val left = parent.paddingStart.toFloat()
            val right = (parent.width - parent.paddingEnd).toFloat()

            for(i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                val layoutParams = child.layoutParams as RecyclerView.LayoutParams
                val top = (child.bottom + layoutParams.bottomMargin + 10).toFloat()
                val bottom = top + 1f

                c.drawRect(left, top, right, bottom, paint)
            }
        }
    }