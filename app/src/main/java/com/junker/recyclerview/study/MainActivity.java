package com.junker.recyclerview.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.junker.recyclerview.study.adapter.GridViewAdapter;
import com.junker.recyclerview.study.adapter.ListViewAdapter;
import com.junker.recyclerview.study.adapter.RecyclerViewBaseAdapter;
import com.junker.recyclerview.study.adapter.StaggerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerViewBaseAdapter mAdapter;

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
                initStaggerViewAdapter(RecyclerView.VERTICAL, false);
                break;
            case R.id.stagger_view_vertical_reverse:
                Log.e(TAG, "点击了StaggerView内的垂直反向");
                initStaggerViewAdapter(RecyclerView.VERTICAL, true);
                break;
            case R.id.stagger_view_horizontal_stander:
                Log.e(TAG, "点击了StaggerView内的水平标准");
                initStaggerViewAdapter(RecyclerView.HORIZONTAL, false);
                break;
            case R.id.stagger_view_horizontal_reverse:
                Log.e(TAG, "点击了StaggerView内的水平反向");
                initStaggerViewAdapter(RecyclerView.HORIZONTAL, true);
                break;
            //多条目类型被点击
            case R.id.more_type:
                Intent intent = new Intent(MainActivity.this,MoreTypeActivity.class);
                startActivity(intent);
        }
        initRecyclerviewItemOnClick();
        return super.onOptionsItemSelected(item);
    }

    private void initListViewAdapter(int orientation, boolean isReverseLayout) {
        linearLayoutManager = new LinearLayoutManager(this, orientation, isReverseLayout);
        mAdapter = new ListViewAdapter(MyApplication.itemBeans);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void initGridViewAdapter(int orientation, boolean isReverseLayout) {
        gridLayoutManager = new GridLayoutManager(this, 2, orientation, isReverseLayout);
        mAdapter = new GridViewAdapter(MyApplication.itemBeans);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void initStaggerViewAdapter(int orientation, boolean isReverseLayout) {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, orientation);
        staggeredGridLayoutManager.setReverseLayout(isReverseLayout);
        mAdapter = new StaggerViewAdapter( MyApplication.itemBeans);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void initRecyclerviewItemOnClick() {
        mAdapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, long id) {
                Toast.makeText(MainActivity.this, "点击的 item position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}