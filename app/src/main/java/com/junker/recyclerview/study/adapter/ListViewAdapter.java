package com.junker.recyclerview.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.junker.recyclerview.study.R;
import com.junker.recyclerview.study.beans.ItemBean;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {

    private Context mContext;
    private List<ItemBean> mData;

    public ListViewAdapter(Context context,List<ItemBean> data){
        this.mContext = context;
        this.mData = data;
    }

    /**
     * 这个方法是用于创建条目的view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ListViewAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_view, parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.InnerHolder holder, int position) {
        holder.mTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getUrl()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mTitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView){
            mImage = itemView.findViewById(R.id.item_view_image);
            mTitle = itemView.findViewById(R.id.item_view_title);
        }
    }
}
