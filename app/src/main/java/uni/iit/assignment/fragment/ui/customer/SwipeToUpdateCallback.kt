package uni.iit.assignment.fragment.ui.customer

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uni.iit.assignment.AssignmentApplication.Companion.context
import uni.iit.assignment.R

abstract class SwipeToUpdateCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    private val background = ColorDrawable(ContextCompat.getColor(context(), R.color.UpdateColor))
    private val updateIcon = ContextCompat.getDrawable(context(), R.drawable.ic_edit)!!
    private val intrinsicWith = updateIcon.intrinsicWidth
    private val intrinsicHeight = updateIcon.intrinsicHeight

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        background.setBounds(
            itemView.right + dY.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        background.draw(c)

        val updateIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val updateIconMargin = (itemHeight - intrinsicHeight) / 2
        val updateIconLeft = itemView.right - updateIconMargin - intrinsicWith
        val updateIconRight = itemView.right - updateIconMargin
        val updaterIconBottom = updateIconTop + intrinsicHeight

        updateIcon.setBounds(updateIconLeft, updateIconTop, updateIconRight, updaterIconBottom)
        updateIcon.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}