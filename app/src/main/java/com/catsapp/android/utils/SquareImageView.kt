package com.catsapp.android.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * A custom ImageView that can enforce a square aspect ratio.
 *
 * This class extends AppCompatImageView and overrides the onMeasure method
 * to optionally enforce a square aspect ratio based on its width.
 *
 * @property isSquare A flag indicating whether the view should be square. Defaults to true.
 */
class SquareImageView : AppCompatImageView {
    private var isSquare = true

    /**
     * Constructor that takes only a context.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     */
    constructor(context: Context) : super(context)

    /**
     * Constructor that takes a context and an attribute set.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs The attributes of the XML tag that is inflating the view.
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * Constructor that takes a context, an attribute set, and a default style attribute.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a reference to a style resource that supplies default values for the view.
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * Measure the view and its content to determine the measured width and the measured height.
     *
     * This method enforces a square aspect ratio based on the view's width if the isSquare flag is true.
     *
     * @param widthMeasureSpec Horizontal space requirements as imposed by the parent.
     * @param heightMeasureSpec Vertical space requirements as imposed by the parent.
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int = measuredWidth
        val height: Int = measuredHeight
        setMeasuredDimension(width, if (isSquare) width else height)
    }

    /**
     * Set whether the view should maintain a square aspect ratio.
     *
     * @param square True if the view should be square, false otherwise.
     */
    fun setSquare(square: Boolean) {
        isSquare = square
    }
}
