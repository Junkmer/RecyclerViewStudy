package com.junker.recyclerview.study;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class JunkerRecyclerview extends RecyclerView {

    private ScrollRefreshListener mScrollRefreshListener;

    public JunkerRecyclerview(@NonNull Context context) {
        this(context,null);
    }

    public JunkerRecyclerview(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JunkerRecyclerview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean isFirstItemVisibleCompleted() {
        LayoutManager layoutManager = getLayoutManager();
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        assert linearLayoutManager != null;
        //获取第一个完全可见的 item position
        int firstPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        //获取最后一个完全可见的 item position
        int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        //获取视图加载的item 数量，该调用和 getAdapter().getItemCount() 获取到值相同
        int layoutCount = linearLayoutManager.getItemCount();
        Log.v("TAG", "isFirstItemVisibleCompleted - firstPosition=" + firstPosition + "|lastPosition=" + lastPosition + "|layoutCount=" + layoutCount);
        if (firstPosition == 0 && ((lastPosition - firstPosition + 1) < layoutCount)) {
            return true;
        }
        return false;
    }

    private boolean isLastItemVisibleCompleted() {
        LayoutManager layoutManager = getLayoutManager();
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        assert linearLayoutManager != null;
        //获取第一个可见的 item position
        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
        //获取最后一个完全可见的 item position
        int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        //获取当前可见的子类 item 数量
        int childCount = linearLayoutManager.getChildCount();
        Log.v("TAG", "isLastItemVisibleCompleted - firstPosition=" + firstPosition + "|lastPosition=" + lastPosition + "|childCount=" + childCount);
        if (lastPosition >= firstPosition + childCount - 1) {
            return true;
        }
        return false;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (state == RecyclerView.SCROLL_STATE_IDLE) { //当前没有滚动
            if (isFirstItemVisibleCompleted()){//下拉刷新
                if (mScrollRefreshListener != null){
                    mScrollRefreshListener.downPullToRefresh();
                }
            }else if (isLastItemVisibleCompleted()){//上拉加载
                if (mScrollRefreshListener != null){
                    mScrollRefreshListener.upPullToLoadMore();
                }
            }
        } else if (state == RecyclerView.SCROLL_STATE_DRAGGING) { //当前正在被外部输入（例如用户触摸输入）拖动。

        } else if (state == RecyclerView.SCROLL_STATE_SETTLING) { //目前正在动画化到最终位置，而不是在外部控制。

        }
    }

    public void setScrollRefreshListener(ScrollRefreshListener listener) {
        this.mScrollRefreshListener = listener;
    }

    public interface ScrollRefreshListener {
        void upPullToLoadMore();
        void downPullToRefresh();
    }
}
