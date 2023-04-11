package com.junker.recyclerview.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.junker.recyclerview.study.adapter.GridViewAdapter;
import com.junker.recyclerview.study.adapter.ListViewAdapter;
import com.junker.recyclerview.study.adapter.StaggerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ListViewAdapter mListViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridViewAdapter mGridViewAdapter;
    private GridLayoutManager gridLayoutManager;
    private StaggerViewAdapter mStaggerViewAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        initListViewAdapter(RecyclerView.VERTICAL, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            //ListView
            case R.id.list_view_vertical_stander:
                Log.e(TAG, "点击了ListView内的垂直标准");
                initListViewAdapter(RecyclerView.VERTICAL, false);
                break;
            case R.id.list_view_vertical_reverse:
                Log.e(TAG, "点击了ListView内的垂直反向");
                initListViewAdapter(RecyclerView.VERTICAL, true);
                break;
            case R.id.list_view_horizontal_stander:
                Log.e(TAG, "点击了ListView内的水平标准");
                initListViewAdapter(RecyclerView.HORIZONTAL, false);
                break;
            case R.id.list_view_horizontal_reverse:
                Log.e(TAG, "点击了ListView内的水平反向");
                initListViewAdapter(RecyclerView.HORIZONTAL, true);
                break;
            //GridView
            case R.id.grid_view_vertical_stander:
                Log.e(TAG, "点击了GridView内的垂直标准");
                initGridViewAdapter(RecyclerView.VERTICAL, false);
                break;
            case R.id.grid_view_vertical_reverse:
                Log.e(TAG, "点击了GridView内的垂直反向");
                initGridViewAdapter(RecyclerView.VERTICAL, true);
                break;
            case R.id.grid_view_horizontal_stander:
                Log.e(TAG, "点击了GridView内的水平标准");
                initGridViewAdapter(RecyclerView.HORIZONTAL, false);
                break;
            case R.id.grid_view_horizontal_reverse:
                Log.e(TAG, "点击了GridView内的水平反向");
                initGridViewAdapter(RecyclerView.HORIZONTAL, true);
                break;
            //StaggerView
            case R.id.stagger_view_vertical_stander:
                Log.e(TAG, "点击了StaggerView内的垂直标准");
                initStaggerViewAdapter(RecyclerView.VERTICAL,false);
                break;
            case R.id.stagger_view_vertical_reverse:
                Log.e(TAG, "点击了StaggerView内的垂直反向");
                initStaggerViewAdapter(RecyclerView.VERTICAL,true);
                break;
            case R.id.stagger_view_horizontal_stander:
                Log.e(TAG, "点击了StaggerView内的水平标准");
                initStaggerViewAdapter(RecyclerView.HORIZONTAL,false);
                break;
            case R.id.stagger_view_horizontal_reverse:
                Log.e(TAG, "点击了StaggerView内的水平反向");
                initStaggerViewAdapter(RecyclerView.HORIZONTAL,true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initListViewAdapter(int orientation, boolean isReverseLayout) {
//        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(this, orientation, isReverseLayout);
            mListViewAdapter = new ListViewAdapter(this, MyApplication.itemBeans);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mListViewAdapter);
//        } else {
//            linearLayoutManager.setOrientation(orientation);
//            linearLayoutManager.setReverseLayout(isReverseLayout);
//            mListViewAdapter.notifyItemRangeChanged(0, mListViewAdapter.getItemCount());
//        }
    }

    private void initGridViewAdapter(int orientation, boolean isReverseLayout) {
//        if (gridLayoutManager == null) {
            gridLayoutManager = new GridLayoutManager(this, 2, orientation, isReverseLayout);
            mGridViewAdapter = new GridViewAdapter(this, MyApplication.itemBeans);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(mGridViewAdapter);
//        } else {
//            gridLayoutManager.setOrientation(orientation);
//            gridLayoutManager.setReverseLayout(isReverseLayout);
////            mGridViewAdapter.notifyItemChanged(0,mListViewAdapter.getItemCount());
//            mGridViewAdapter.notifyItemRangeChanged(0, mGridViewAdapter.getItemCount());
//        }
    }

    private void initStaggerViewAdapter(int orientation, boolean isReverseLayout) {
//        if (staggeredGridLayoutManager == null) {
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, orientation);
            staggeredGridLayoutManager.setReverseLayout(isReverseLayout);
            mStaggerViewAdapter = new StaggerViewAdapter(this, MyApplication.itemBeans);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(mStaggerViewAdapter);
//        } else {
//            staggeredGridLayoutManager.setOrientation(orientation);
//            staggeredGridLayoutManager.setReverseLayout(isReverseLayout);
////            mStaggerViewAdapter.notifyItemChanged(0,mListViewAdapter.getItemCount());
//            mStaggerViewAdapter.notifyItemRangeChanged(0, mStaggerViewAdapter.getItemCount());
//        }
    }
}