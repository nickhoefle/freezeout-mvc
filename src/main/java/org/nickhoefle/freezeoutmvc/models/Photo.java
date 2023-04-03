package org.nickhoefle.freezeoutmvc.models;

import jakarta.persistence.Entity;

@Entity
public class Photo extends AbstractEntity {

    private String title;

    private String url;

    private int orderNumber;

    private String credit;

    public Photo () { }

    public Photo(String title, String url, int orderNumber, String credit) {
        this.title = title;
        this.url = url;
        this.orderNumber = orderNumber;
        this.credit = credit;
    }

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

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", orderNumber=" + orderNumber +
                ", credit='" + credit + '\'' +
                '}';
    }
}
