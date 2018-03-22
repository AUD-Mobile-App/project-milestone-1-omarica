package com.omarica.bucketlist;

/**
 * Created by omarica on 3/21/18.
 */

public class BucketItem {

    private String key;
    private String name;
    private String description;
    private String imgUrl;
    private String location;
    private boolean status;
    private String dueDate;



    public BucketItem() {

    }


    public BucketItem(String name, String description, String imgUrl, String location, boolean status, String key, String dueDate) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.location = location;
        this.status = status;
        this.key = key;
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
