package com.junker.recyclerview.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.junker.recyclerview.study.R;
import com.junker.recyclerview.study.beans.ItemBean;

import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.InnerHolder> {

    private Context mContext;
    private List<ItemBean> mData;

    public GridViewAdapter(Context context, List<ItemBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    /**
     * 这个方法是用于创建条目的view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public GridViewAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewAdapter.InnerHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            mImage = itemView.findViewById(R.id.item_view_image);
            mTitle = itemView.findViewById(R.id.item_view_title);
        }

        public void setData(ItemBean bean) {
            mTitle.setText(bean.getTitle());
            Glide.with(mContext).load(bean.getUrl()).into(mImage);
        }
    }
}
