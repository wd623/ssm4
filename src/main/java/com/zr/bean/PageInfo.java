package com.zr.bean;

import java.util.List;

public class PageInfo<T> {
    private List<T> list;
    private int totalCount;
    private int size;
    private int totalPage;
    private int currentPage;

    public PageInfo() {
    }

    public PageInfo(List<T> list, int totalCount, int size, int totalPage, int currentPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.size = size;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                ", size=" + size +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
