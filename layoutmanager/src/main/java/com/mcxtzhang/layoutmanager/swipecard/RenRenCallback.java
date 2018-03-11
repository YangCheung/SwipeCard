package com.mcxtzhang.layoutmanager.swipecard;

import android.support.v7.widget.RecyclerView;
import com.mcxtzhang.layoutmanager.helper.ItemTouchHelper;
import java.util.List;


public class RenRenCallback extends ItemTouchHelper.SimpleCallback {

    protected RecyclerView mRv;
    protected List mDatas;
    protected RecyclerView.Adapter mAdapter;

    public RenRenCallback(RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        this(0,
                ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                rv, adapter, datas);
    }

    public RenRenCallback(int dragDirs, int swipeDirs
            , RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(dragDirs, swipeDirs);
        mRv = rv;
        mAdapter = adapter;
        mDatas = datas;
    }

    public float getThreshold(RecyclerView.ViewHolder viewHolder) {
        return mRv.getWidth() * /*getSwipeThreshold(viewHolder)*/ 0.5f;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(ItemTouchHelper touchHelper, RecyclerView.ViewHolder viewHolder, int direction) {

    }

}
