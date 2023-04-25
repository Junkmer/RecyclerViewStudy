package com.junker.recyclerview.study.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.junker.recyclerview.study.R;
import com.junker.recyclerview.study.beans.ItemBean;

import java.util.List;

public class GridViewAdapter extends RecyclerViewBaseAdapter {

    public GridViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        return View.inflate(parent.getContext(), R.layout.item_grid_view, null);
    }
}
