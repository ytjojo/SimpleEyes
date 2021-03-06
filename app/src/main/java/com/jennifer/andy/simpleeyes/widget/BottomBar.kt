package com.jennifer.andy.simpleeyes.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout


/**
 * Author:  andy.xwt
 * Date:    2017/9/27 22:00
 * Description: 底部导航栏
 */
class BottomBar : LinearLayout {

    private lateinit var mTabLayout: LinearLayout
    private lateinit var mTabParams: LinearLayout.LayoutParams
    private var mCurrentPosition = 0//当前默认位置
    private var mBottomItemLayouts = mutableListOf<BottomItemLayout>()

    private var mListener: onTabSelectedListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        orientation = LinearLayout.HORIZONTAL

        mTabLayout = LinearLayout(context)
        mTabLayout.setBackgroundColor(Color.WHITE)
        mTabLayout.orientation = HORIZONTAL
        mTabParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        mTabParams.weight = 1f

        addView(mTabLayout, mTabParams)
    }

    fun addItem(bottomItem: BottomItem) {

        val bottomItemLayout = BottomItemLayout(context)
        bottomItemLayout.setTabPosition(mBottomItemLayouts.size)
        bottomItemLayout.setBottomItem(bottomItem)
        mTabLayout.addView(bottomItemLayout, mTabParams)
        mBottomItemLayouts.add(bottomItemLayout)

        bottomItemLayout.setOnClickListener({
            val position = bottomItemLayout.getTabPosition()
            if (position == mCurrentPosition) {
                mListener?.onTabReselected(position)
            } else {
                mListener?.onTabSelected(position, mCurrentPosition)
                bottomItemLayout.isSelected = true
                mListener?.onTabUnselected(mCurrentPosition)
                mBottomItemLayouts[mCurrentPosition].isSelected = false
                mCurrentPosition = position
            }
        })
    }


    fun setOnTabSelectedListener(onTabSelectedListener: onTabSelectedListener) {
        mListener = onTabSelectedListener
    }

    /**
     * 选项卡选择监听
     */
    interface onTabSelectedListener {

        /**
         * 当选项卡被选择
         */
        fun onTabSelected(position: Int, prePosition: Int)

        /**
         * 当前选项卡没有被选择
         */
        fun onTabUnselected(position: Int)

        /**
         * 当选项卡被重复选择
         */
        fun onTabReselected(position: Int)
    }

    /**
     * 初始化
     */
    fun initialise() {
        mBottomItemLayouts[mCurrentPosition].isSelected = true
    }
}

