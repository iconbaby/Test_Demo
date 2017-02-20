package com.slkk.test_retrofit;

/**
 * Created by dell on 2017/2/16.
 */

public class DataBean {
    private String status;
    private String url;
    private String latest;
    public void setUrl(String url) {

        this.url = url;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastest(String lastest) {
        this.latest = lastest;
    }



    public String getLastest() {
        return latest;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.getLastest();
    }
}
