package com.cloudhearing.ealbum.utils;

import java.util.Map;

public class JpushSimpleMsg {

    private int msg_content;
    private String content_type;
    private String title;
    private Map<String, Object> extras;

    public int getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(int msg_content) {
        this.msg_content = msg_content;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "JpushSimpleMsg{" +
                "msg_content='" + msg_content + '\'' +
                ", content_type='" + content_type + '\'' +
                ", title='" + title + '\'' +
                ", extras=" + extras +
                '}';
    }
}
