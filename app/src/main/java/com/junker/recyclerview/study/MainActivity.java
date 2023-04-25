package com.junker.recyclerview.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.junker.recyclerview.study.adapter.GridViewAdapter;
import com.junker.recyclerview.study.adapter.ListViewAdapter;
import com.junker.recyclerview.study.adapter.RecyclerViewBaseAdapter;
import com.junker.recyclerview.study.adapter.StaggerViewAdapter;
import com.junker.recyclerview.study.beans.ItemBean;
import com.junker.recyclerview.study.beans.MoreTypeBean;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private JunkerRecyclerview recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerViewBaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initEvent() {
        recyclerView.setScrollRefreshListener(new JunkerRecyclerview.ScrollRefreshListener() {
            @Override
            public void downPullToRefresh() {
                Log.e(TAG, "触发下拉刷新回调");
                if (mAdapter.getDownRefreshState() == RecyclerViewBaseAdapter.TYPE_REFRESH_VISIBLE_STATE){
                    Log.e(TAG, "当前已处于下拉刷新状态，无需重复处理");
                    return;
                }
                mAdapter.updateDownRefreshStatue(RecyclerViewBaseAdapter.TYPE_REFRESH_VISIBLE_STATE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random ranNum = new Random();
                        int num = ranNum.nextInt(2) ;
                        if (num == 1) {
                            ArrayList<ItemBean> itemBeans = new ArrayList<>();
                            for (int i = 0; i < MyApplication.GROUP_FACE_COUNT; i++) {
                                ItemBean imageBean = new ItemBean();
                                imageBean.setTitle(String.format("我是第%s条新数据Title", i + 1));
                                Random random = new Random();
                                int randomValue = random.nextInt(30);
                                imageBean.setUrl(String.format(MyApplication.GROUP_FACE_URL, randomValue));
                                itemBeans.add(imageBean);
                            }
                            mAdapter.setData(itemBeans);
                        }else {
                            Log.e(TAG, "刷新失败");
                            Toast.makeText(MainActivity.this,"刷新失败",Toast.LENGTH_SHORT).show();
                            mAdapter.updateDownRefreshStatue(RecyclerViewBaseAdapter.TYPE_REFRESH_GONE_STATE);
                        }
                    }
                }, 2000);
            }

            @Override
            public void upPullToLoadMore() {
                Log.e(TAG, "触发上拉加载更多回调");
                if (mAdapter.getUpLoadState() == RecyclerViewBaseAdapter.TYPE_LOADING_STATE){
                    Log.e(TAG, "当前已处于上拉加载更多状态，无需重复处理");
                    return;
                }
                mAdapter.updateUpLoadStatue(RecyclerViewBaseAdapter.TYPE_LOADING_STATE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random ranNum = new Random();
                        int num = ranNum.nextInt(3) -1;
                        if (num == RecyclerViewBaseAdapter.TYPE_LOADING_STATE) {
                            ItemBean imageBean = new ItemBean();
                            imageBean.setTitle("我是新增的数据Title");

                            Random random = new Random();
                            imageBean.setUrl(String.format(MyApplication.GROUP_FACE_URL, (random.nextInt(19) + 1) + ""));

                            mAdapter.addData(imageBean);
                        }else {
                            mAdapter.updateUpLoadStatue(num);
                        }
                    }
                }, 2000);
            }
        });
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
                Intent intent = new Intent(MainActivity.this, MoreTypeActivity.class);
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
        mAdapter = new StaggerViewAdapter(MyApplication.itemBeans);
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