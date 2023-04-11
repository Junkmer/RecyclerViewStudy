package com.junker.recyclerview.study.beans;

import java.io.Serializable;

public class MoreTypeBean implements Serializable {
    private String title;
    private String url;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
