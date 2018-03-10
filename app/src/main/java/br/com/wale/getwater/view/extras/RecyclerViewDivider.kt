package br.com.wale.getwater.view.extras

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Admin on 03/08/17.
 */
class RecyclerViewDivider(val context: Context, resId: Int) : RecyclerView.ItemDecoration(){

    private val divider = ContextCompat.getDrawable(context, resId)

//    init {
//        val a = context.obtainStyledAttributes(ATTRS)
//        divider = a.getDrawable(0)
//        a.recycle()
//
//    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)

        var left = parent!!.paddingLeft
        var right = parent!!.width - parent?.paddingRight

        val childCount = parent!!.childCount
        for(i in 0..childCount - 1){
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)

        }

    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        //super.getItemOffsets(outRect, view, parent, state)
        outRect?.let{

            outRect.set(0, 0, 0, 15)

        }
    }

    companion object {

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

}