package com.bo.imall.model.common;

import java.io.Serializable;
import java.util.List;

public class PageList<T extends Serializable> {

    private List<T> list;

    private int count;

    public PageList() {
    }

    public PageList(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
