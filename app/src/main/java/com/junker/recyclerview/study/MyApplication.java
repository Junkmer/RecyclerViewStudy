package com.junker.recyclerview.study;

import android.app.Application;

import com.junker.recyclerview.study.beans.ItemBean;

import java.util.ArrayList;

public class MyApplication extends Application {

    public static final String GROUP_FACE_URL = "https://im.sdk.cloud.tencent.cn/download/tuikit-resource/group-avatar/group_avatar_%s.png";
    public static final int GROUP_FACE_COUNT = 24;
    public static ArrayList<ItemBean> itemBeans;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        itemBeans = new ArrayList<>();
        for (int i = 0; i < GROUP_FACE_COUNT; i++) {
            ItemBean imageBean = new ItemBean();

            long randomValue = (long) (1 + Math.random() * (5 - 1 + 1));
            long result = getResult(2, randomValue);
            StringBuilder content = new StringBuilder();
            for (int j = 0; j < result; j++) {
                content.append("啦啦队");
            }

            imageBean.setTitle(String.format("我是第%s条数据Title", (i + 1) + "") + content);
            imageBean.setUrl(String.format(GROUP_FACE_URL, (i + 1) + ""));
            itemBeans.add(imageBean);
        }
    }

    /**
     * 思路：利用递归实现
     * Java 版本：计算一个数的n次幂的方法
     *
     * @param number 要计算的数
     * @param count  要计算数的幂
     */
    public static long getResult(long number, long count) {
        if (count > 0) {
            return number * getResult(number, count - 1);
        }
        return 1;
    }
}
