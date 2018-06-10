package com.vipul.bit_hotels.model;

/**
 * Created by vipulkumar on 19/08/16.
 */
public class LocationItem {
    private String title;
    private String code;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String subTitle;
}
