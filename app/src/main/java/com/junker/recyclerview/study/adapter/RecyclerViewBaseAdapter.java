package com.junker.recyclerview.study.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.junker.recyclerview.study.R;
import com.junker.recyclerview.study.beans.ItemBean;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;
    /**
     * 下拉刷新类型
     */
    private final static int DOWN_VIEW_REFRESH_TYPE = -100;//显示下拉视图
    /**
     * refresh 刷新状态
     */
    public final static int TYPE_REFRESH_VISIBLE_STATE = 0;//显示刷新UI
    public final static int TYPE_REFRESH_GONE_STATE = 1;//隐藏刷新UI
    /**
     * 上拉加载更多类型
     */
    private final static int UP_VIEW_LOAD_MORE_TYPE = -99;//显示上拉视图
    /**
     * loading 条加载状态
     */
    public final static int TYPE_LOAD_DEFAULT_STATE = 8 >> 1;//默认状态
    public final static int TYPE_LOADING_STATE = 0;//正在加载
    public final static int TYPE_LOAD_FAIL_STATE = -1;//加载失败
    public final static int TYPE_LOAD_COMPLETE_STATE = 1;//已全部加载
    /**
     * 当前上拉、下来 loading 布局状态
     */
    private int mUpLoadState = TYPE_LOAD_DEFAULT_STATE;
    private int mDownRefreshState = TYPE_REFRESH_GONE_STATE;

    public RecyclerViewBaseAdapter(List<ItemBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        if (viewType >= 0){
            view = getSubView(parent, viewType);
            holder = new NormalHolder(view);
        }else if(viewType == UP_VIEW_LOAD_MORE_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more_progress_view,parent,false);
            holder = new LoadHolder(view);
        }else if (viewType == DOWN_VIEW_REFRESH_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_refresh_data_view,parent,false);
            holder = new RefreshHolder(view);
        }
        assert holder != null;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder){
            ((NormalHolder)holder).setData(mData.get(position-1));
            initEvent(holder.itemView, position, holder.getItemId());
        }else if (holder instanceof LoadHolder){
            ((LoadHolder)holder).updateLoadState(mUpLoadState);
        }else if (holder instanceof RefreshHolder){
            ((RefreshHolder)holder).updateRefreshState(mDownRefreshState);
        }
    }

    private void initEvent(View itemView, int postion, long viewId) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, postion, viewId);
                }
            }
        });
    }

    public List<ItemBean> getData() {
        return mData;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ItemBean> itemBeans){
        if (mData == null){
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(itemBeans);
        mDownRefreshState = TYPE_REFRESH_GONE_STATE;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(ItemBean bean){
        if (mData != null){
            mData.add(mData.size(),bean);
        }
        mUpLoadState = TYPE_LOAD_DEFAULT_STATE;
        notifyDataSetChanged();
    }

    public void addAllData(int addType,List<ItemBean> beans){
        mData.addAll(0,beans);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size() + 2;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null && position == mData.size()+1) {
            return UP_VIEW_LOAD_MORE_TYPE;
        }else if (position == 0){
            return DOWN_VIEW_REFRESH_TYPE;
        }
        return position;
    }

    public int getUpLoadState() {
        return mUpLoadState;
    }

    public int getDownRefreshState() {
        return mDownRefreshState;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateUpLoadStatue(int stateType){
        this.mUpLoadState = stateType;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateDownRefreshStatue(int stateType){
        this.mDownRefreshState = stateType;
        notifyDataSetChanged();
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    public static class NormalHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;

        public NormalHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        public void setData(ItemBean bean) {
            mTitle.setText(bean.getTitle());
            Glide.with(itemView.getContext()).load(bean.getUrl()).into(mImage);
        }

        private void initView(View itemView) {
            mImage = itemView.findViewById(R.id.item_view_image);
            mTitle = itemView.findViewById(R.id.item_view_title);
        }
    }

    private static class RefreshHolder extends RecyclerView.ViewHolder{
        private static final String TAG = RefreshHolder.class.getSimpleName();

        private View view;

        public RefreshHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            initView(itemView);
        }

        private void initView(View itemView) {
        }

        public void updateRefreshState(int stateType){
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            switch (stateType){
                case TYPE_REFRESH_GONE_STATE:
                    param.height = 0;
                    param.width = 0;
                    itemView.setVisibility(View.GONE);
                    break;
                case TYPE_REFRESH_VISIBLE_STATE:
                    param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    itemView.setVisibility(View.VISIBLE);
                    break;
            }
            itemView.setLayoutParams(param);
        }
    }

    private static class LoadHolder extends RecyclerView.ViewHolder{
        private static final String TAG = LoadHolder.class.getSimpleName();
        private LinearLayout mLoadProgressLayout;
        private TextView mLoadStateView;

        public LoadHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            mLoadProgressLayout = itemView.findViewById(R.id.load_progress_layout);
            mLoadStateView = itemView.findViewById(R.id.load_state_view);
        }

        public void updateLoadState(int stateType){
            Log.e(TAG,"stateType = "+stateType);
            switch (stateType){
                case TYPE_LOADING_STATE:
                    mLoadProgressLayout.setVisibility(View.VISIBLE);
                    mLoadStateView.setVisibility(View.INVISIBLE);
                    break;
                case TYPE_LOAD_COMPLETE_STATE:
                    mLoadProgressLayout.setVisibility(View.INVISIBLE);
                    mLoadStateView.setVisibility(View.VISIBLE);
                    mLoadStateView.setText("已全部加载");
                    break;
                case TYPE_LOAD_FAIL_STATE:
                    mLoadProgressLayout.setVisibility(View.INVISIBLE);
                    mLoadStateView.setVisibility(View.VISIBLE);
                    mLoadStateView.setText("加载失败，点击重试！");
                    break;
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, long id);
    }
}
