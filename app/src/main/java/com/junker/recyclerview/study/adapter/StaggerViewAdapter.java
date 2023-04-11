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

public class StaggerViewAdapter extends RecyclerViewBaseAdapter {

    public StaggerViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stagger_view, parent, false);
    }

}
