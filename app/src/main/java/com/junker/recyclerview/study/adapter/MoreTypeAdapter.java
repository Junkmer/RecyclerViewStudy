package com.junker.recyclerview.study.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.junker.recyclerview.study.R;
import com.junker.recyclerview.study.beans.MoreTypeBean;

import java.util.ArrayList;
import java.util.List;

public class MoreTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //定义三个常量类型，因为有三种布局类型
    public static final int TYPE_FULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;

    private List<MoreTypeBean> mDatas;

    public MoreTypeAdapter(List<MoreTypeBean> typeBeans) {
        this.mDatas = typeBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FULL_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_full_view, parent, false);
            return new FullViewHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_right_view, parent, false);
            return new RightViewHolder(view);
        } else if (viewType == TYPE_THREE_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_three_view, parent, false);
            return new ThreeViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FullViewHolder) {
            ((FullViewHolder) holder).setData(mDatas.get(position));
        } else if (holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).setData(mDatas.get(position));
        } else if (holder instanceof ThreeViewHolder) {
            ((ThreeViewHolder) holder).setData(mDatas.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = mDatas.get(position);
        if (moreTypeBean.getType() == 0) {
            return TYPE_FULL_IMAGE;
        } else if (moreTypeBean.getType() == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGE;
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public List<MoreTypeBean> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<MoreTypeBean> mDatas) {
        this.mDatas = mDatas;
    }

    public void addData(MoreTypeBean bean) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.add(bean);
    }

    private static class FullViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;

        public FullViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        public void setData(MoreTypeBean moreTypeBean) {
            mTitle.setText(moreTypeBean.getTitle());
            Glide.with(itemView.getContext()).load(moreTypeBean.getUrl()).into(mImage);
        }

        private void initView(View itemView) {
            mImage = itemView.findViewById(R.id.imageView);
            mTitle = itemView.findViewById(R.id.textView);
        }
    }

    private static class RightViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        public void setData(MoreTypeBean moreTypeBean) {
            mTitle.setText(moreTypeBean.getTitle());
            Glide.with(itemView.getContext()).load(moreTypeBean.getUrl()).into(mImage);
        }

        private void initView(View itemView) {
            mImage = itemView.findViewById(R.id.imageView);
            mTitle = itemView.findViewById(R.id.textView);
        }
    }

    private static class ThreeViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        ImageView mImage1;
        ImageView mImage2;
        ImageView mImage3;

        public ThreeViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        public void setData(MoreTypeBean moreTypeBean) {
            mTitle.setText(moreTypeBean.getTitle());
            Glide.with(itemView.getContext()).load(moreTypeBean.getUrl()).into(mImage1);
            Glide.with(itemView.getContext()).load(moreTypeBean.getUrl()).into(mImage2);
            Glide.with(itemView.getContext()).load(moreTypeBean.getUrl()).into(mImage3);
        }

        private void initView(View itemView) {
            mTitle = itemView.findViewById(R.id.textView);
            mImage1 = itemView.findViewById(R.id.imageView1);
            mImage2 = itemView.findViewById(R.id.imageView2);
            mImage3 = itemView.findViewById(R.id.imageView3);
        }
    }
}
