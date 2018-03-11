package com.mcxtzhang.flowlayoutmanager.tantan;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import com.mcxtzhang.layoutmanager.helper.ItemTouchHelper;
import java.util.List;

/**
 * 介绍：探探效果定制的Callback
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 16/12/18.
 */

public class TanTanCallback extends ItemTouchHelper.SimpleCallback {
    int mHorizontalDeviation;

    protected RecyclerView mRv;
    protected List mDatas;
    protected RecyclerView.Adapter mAdapter;

    public TanTanCallback(RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(0, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        //rv.setItemAnimator(null);
        mRv = rv;
        mAdapter = adapter;
        mDatas = datas;

        mHorizontalDeviation = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mRv.getContext().getResources().getDisplayMetrics());
    }

    //public TanTanCallback(int dragDirs, int swipeDirs, RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
    //    super(dragDirs, swipeDirs, rv, adapter, datas);
    //}

    public int getHorizontalDeviation() {
        return mHorizontalDeviation;
    }

    public TanTanCallback setHorizontalDeviation(int horizontalDeviation) {
        mHorizontalDeviation = horizontalDeviation;
        return this;
    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        int index = viewHolder.getLayoutPosition();
        //if(index == 0 || index)
        if (isTopViewCenterInHorizontal(viewHolder.itemView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeThreshold(viewHolder);
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        View topView = mRv.getChildAt(mRv.getChildCount() - 1);
        if (isTopViewCenterInHorizontal(topView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeEscapeVelocity(defaultValue);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        View topView = mRv.getChildAt(mRv.getChildCount() - 1);
        if (isTopViewCenterInHorizontal(topView)) {
            return Float.MAX_VALUE;
        }
        return super.getSwipeVelocityThreshold(defaultValue);
    }


    public boolean isTopViewCenterInHorizontal(View topView) {
       return Math.abs(mRv.getWidth() / 2 - topView.getX() - (topView.getWidth() / 2)) < mHorizontalDeviation;
    }


    @Override
    public void onSwiped(ItemTouchHelper touchHelper, RecyclerView.ViewHolder viewHolder, int direction) {

        //if(direction == ItemTouchHelper.DOWN || direction == ItemTouchHelper.UP) {
        //    mAdapter.notifyDataSetChanged();
        //    return;
        //}
        //
        //touchHelper.removeAnimation(viewHolder);
        //viewHolder.itemView.setTranslationX(0);
        //viewHolder.itemView.setTranslationY(0);
        //int nextPosition = viewHolder.getLayoutPosition();
        //if(direction == ItemTouchHelper.LEFT) {
        //    nextPosition ++;
        //}
        //if (nextPosition == mRv.getAdapter().getItemCount() - 1) {
        //    setDefaultSwipeDirs(ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP);
        //} else {
        //    setDefaultSwipeDirs(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP);
        //}
        //mRv.scrollToPosition(nextPosition);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }
}
