package uni.iit.assignment.fragment.ui.employee

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uni.iit.assignment.AssignmentApplication
import uni.iit.assignment.R

class SwipeToUpdateItemDecoration : RecyclerView.ItemDecoration() {
    private val context = AssignmentApplication.context()
    private val background = ColorDrawable(ContextCompat.getColor(context, R.color.UpdateColor))

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.itemAnimator?.isRunning == true) {
            val itemAfterUpdatedItem = getItemAfterUpdatedItem(parent)

            val left = parent.width;
            val right = 0
            var top = 0
            var bottom = 0
            if (itemAfterUpdatedItem != null) {
                top = itemAfterUpdatedItem.top
                bottom = (itemAfterUpdatedItem.top + itemAfterUpdatedItem.translationY).toInt()
            }
            background.setBounds(left, top, right, bottom)
            background.draw(c)

        }
        super.onDraw(c, parent, state)
    }

    private fun getItemAfterUpdatedItem(recyclerView: RecyclerView): View? {
        val childCount = recyclerView.layoutManager!!.childCount
        for(i in 0 until childCount) {
            val child = recyclerView.layoutManager!!.getChildAt(i)
            if (child != null && child.translationY > 0) {
                return child
            }
        }
        return null
    }
}