package com.util;

/**
 * @author 王建兵
 * @Classname Page
 * @Description TODO
 * @Date 2019/8/11 8:25
 * @Created by Administrator
 */
public class Page {
    private int page=1;  //页码
    private int rows=3; //页大小

    public Page() {
    }

    public Page(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
