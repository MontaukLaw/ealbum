package com.cloudhearing.ealbum.utils;

public class PushJsonMsg {

    private String platform;
    private JpushAudience audience;
    private JpushSimpleMsg message;

    public PushJsonMsg() {
        this.platform = "all";
        this.audience = new JpushAudience();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public JpushAudience getAudience() {
        return audience;
    }

    public void setAudience(JpushAudience audience) {
        this.audience = audience;
    }

    public JpushSimpleMsg getMessage() {
        return message;
    }

    public void setMessage(JpushSimpleMsg message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PushJsonMsg{" +
                "platform='" + platform + '\'' +
                ", audience=" + audience +
                ", message=" + message +
                '}';
    }

}
