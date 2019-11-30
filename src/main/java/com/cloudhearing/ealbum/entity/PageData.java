package com.cloudhearing.ealbum.entity;

public class PageData {
    private int pagesize, pageno;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "pagesize=" + pagesize +
                ", pageno=" + pageno +
                '}';
    }
}
