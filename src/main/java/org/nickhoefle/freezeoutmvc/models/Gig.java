package org.nickhoefle.freezeoutmvc.models;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Gig extends AbstractEntity {

    private String name;

    private Date date;

    private String address;

    private String image;

    public Gig(String name, Date date, String address, String image) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.image = image;
    }

    public Gig () { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Gig{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
