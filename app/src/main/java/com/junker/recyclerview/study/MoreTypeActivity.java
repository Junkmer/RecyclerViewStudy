package com.junker.recyclerview.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import com.junker.recyclerview.study.adapter.MoreTypeAdapter;
import com.junker.recyclerview.study.beans.ItemBean;
import com.junker.recyclerview.study.beans.MoreTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoreTypeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MoreTypeBean> mTypemTBeans;
    private SwipeRefreshLayout mRefreshLayout;
    private MoreTypeAdapter mTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type);

        initView();

        initData();

        initEvent();

        initAdapter();
    }

    private void initEvent() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        MoreTypeBean typeBean = new MoreTypeBean();

                        Random random = new Random();
                        typeBean.setType(random.nextInt(3));

                        typeBean.setTitle("我是新增的数据Title");
                        typeBean.setUrl(String.format(MyApplication.GROUP_FACE_URL, (random.nextInt() + 1) + ""));
                        mTypeAdapter.addData(typeBean);

                        mRefreshLayout.setRefreshing(false);
                        mTypeAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }

    private void initAdapter() {
        //创建和设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MoreTypeActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //初始化适配器
        mTypeAdapter = new MoreTypeAdapter(mTypemTBeans);
        mRecyclerView.setAdapter(mTypeAdapter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.more_type_recyclerview);
        mRefreshLayout = findViewById(R.id.swipe_refresh_layout);
    }

    private void initData() {
        mTypemTBeans = new ArrayList<>();
        for (int i = 0; i < MyApplication.GROUP_FACE_COUNT; i++) {
            MoreTypeBean typeBean = new MoreTypeBean();

            Random random = new Random();
            typeBean.setType(random.nextInt(3));


            long randomValue = (long) (1 + Math.random() * (5 - 1 + 1));
            long result = MyApplication.getResult(2, randomValue);
            StringBuilder content = new StringBuilder();
            for (int j = 0; j < result; j++) {
                content.append("啦啦队");
            }

            typeBean.setTitle(String.format("我是第%s条数据Title", (i + 1) + "") + content);
            typeBean.setUrl(String.format(MyApplication.GROUP_FACE_URL, (i + 1) + ""));
            mTypemTBeans.add(typeBean);
        }

    }
}