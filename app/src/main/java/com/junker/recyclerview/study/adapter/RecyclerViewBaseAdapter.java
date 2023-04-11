package com.junker.recyclerview.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.junker.recyclerview.study.R;
import com.junker.recyclerview.study.beans.ItemBean;

import java.util.List;

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerViewBaseAdapter.BaseHolder> {

    private final List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewBaseAdapter(List<ItemBean> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        return new BaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.setData(mData.get(position));
        initEvent(holder.itemView, position, holder.getItemId());
    }

    private void initEvent(View itemView, int postion, long viewId) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(v, postion, viewId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    public class BaseHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;

        public BaseHolder(@NonNull View itemView) {
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, long id);
    }
}
