package com.mcxtzhang.layoutmanager.swipecard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 介绍：参考人人影视 最多排列四个
 * 重叠卡片布局
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 16/12/15.
 */

public class OverLayCardLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "swipecard";
    public static final int NO_POSITION = -1;
    int mPendingScrollPosition = 3;//NO_POSITION;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void scrollToPosition(int position) {
        Log.d("scrollToPosition", "position = " + position);
        mPendingScrollPosition = position;
        if (mPendingScrollPosition < 0 || mPendingScrollPosition >= getItemCount()) {
            mPendingScrollPosition = NO_POSITION;
        } else {
            requestLayout();
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        Log.e(TAG, "onLayoutChildren() called with: recycler = [" + recycler + "], state = [" + state + "]");
        detachAndScrapAttachedViews(recycler);
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            removeAndRecycleView(child, recycler);
        }
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return;
        }

        int startPosition = 0;
        if (mPendingScrollPosition >= 1) {
            startPosition = mPendingScrollPosition - 1;
        }

        int bottomPosition = startPosition + CardConfig.MAX_SHOW_COUNT;
        if (bottomPosition >= itemCount) {
            bottomPosition = itemCount - 1;
        }

        Log.d("onLayoutChildren", "startPosition = " + startPosition + "  bottomPosition = " + bottomPosition + "   itemCount = " + itemCount);
        for (int position = bottomPosition; position >= startPosition; position --) {
            View view = recycler.getViewForPosition(position);

            Log.d("onLayoutChildren", "position = " + position + "  TranslationX = " + view.getTranslationX());

            addView(view);

            Log.d("onLayoutChildren", "position = " + position + "  TranslationX = " + view.getTranslationX());

            measureChildWithMargins(view, 0, 0);
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);

            if(mPendingScrollPosition != 0 && position == startPosition) {
                layoutDecoratedWithMargins(view, widthSpace / 2 - getTotalSpace(), heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view) - getTotalSpace(),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view));
            } else {
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view));
            }

            view.setTranslationX(0);
            view.setTranslationY(0);

        }
    }

    private int getTotalSpace() {
        Log.d("getTotalSpace", "getWidth = " + getWidth());
        return getWidth();// - getPaddingLeft() - getPaddingRight();
    }

}
