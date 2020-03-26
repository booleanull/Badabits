package com.booleanull.core_ui.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.booleanull.core_ui.R
import kotlin.math.tan


class PlaceholderView(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var circle: Boolean = false

    @Dimension
    private var radius: Float? = null
    private var colors = intArrayOf(
        ContextCompat.getColor(context, R.color.design_default_color_primary_dark),
        ContextCompat.getColor(context, R.color.design_default_color_primary),
        ContextCompat.getColor(context, R.color.design_default_color_primary_dark)
    )

    private var valueAnimator: ValueAnimator? = null

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val shaderMatrix = Matrix()

    fun start() {
        valueAnimator?.start()
        isVisible = true
    }

    fun stop() {
        valueAnimator?.cancel()
        isVisible = false
    }

    fun setValueAnimator(animator: ValueAnimator) {
        valueAnimator = animator
    }

    fun setColors(arrays: IntArray) {
        colors = arrays
        updateShader()
    }

    fun setRadius(radius: Float) {
        this.radius = radius
    }

    fun setCircle(circle: Boolean) {
        this.circle = circle
    }

    init {
        isVisible = false
        context.obtainStyledAttributes(attrs, R.styleable.PlaceholderView).apply {
            circle = getBoolean(R.styleable.PlaceholderView_placeholderCircle, false)
            radius = getDimension(R.styleable.PlaceholderView_placeholderRadius, 0f)
            colors = intArrayOf(
                getColor(R.styleable.PlaceholderView_placeholderColorFirst, colors[0]),
                getColor(R.styleable.PlaceholderView_placeholderColorSecond, colors[1]),
                getColor(R.styleable.PlaceholderView_placeholderColorThird, colors[2])
            )
            recycle()
        }

        valueAnimator = getDefaultValueAnimator().apply {
            addUpdateListener {
                invalidate()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = paddingLeft + paddingRight + minimumWidth
        val desiredHeight = minimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateShader()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val tiltTan =
            tan(Math.toRadians(345.0)).toFloat()
        val translateWidth: Float =
            context.resources.displayMetrics.widthPixels.toFloat() + tiltTan * 100
        val animatedValue = valueAnimator?.animatedFraction ?: 0f
        val dx = offset(-translateWidth, translateWidth, animatedValue)
        val dy = 0f

        shaderMatrix.reset()
        shaderMatrix.setRotate(345f, width.toFloat() / 2, height.toFloat() / 2f)
        shaderMatrix.postTranslate(dx, dy)
        paint.shader.setLocalMatrix(shaderMatrix)

        when {
            radius == null -> canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
            circle -> canvas.drawCircle(
                width.toFloat() / 2,
                height.toFloat() / 2,
                width.toFloat() / 2,
                paint
            )
            radius != null -> canvas.drawRoundRect(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                radius!!,
                radius!!,
                paint
            )
        }
    }

    private fun updateShader() {
        paint.shader = LinearGradient(
            0f,
            0f,
            context.resources.displayMetrics.widthPixels.toFloat(),
            0f,
            colors,
            floatArrayOf(0.1f, 0.9f, 1f),
            Shader.TileMode.CLAMP
        )
    }

    private fun offset(
        start: Float,
        end: Float,
        percent: Float
    ): Float {
        return start + (end - start) * percent
    }

    companion object {
        fun getDefaultValueAnimator(): ValueAnimator {
            return ValueAnimator.ofFloat(0f, 1f).apply {
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                duration = 1500
                interpolator = AccelerateDecelerateInterpolator()
            }
        }
    }
}