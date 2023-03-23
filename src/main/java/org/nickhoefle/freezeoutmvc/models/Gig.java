package org.nickhoefle.freezeoutmvc.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Gig extends AbstractEntity {

    @Size(min = 5, message = "Please enter a name for this gig!")
    private String name;

    @NotNull(message = "Please select a date for this gig!")
    private LocalDate date;

    private String time;

    private String address;

    private String image;

    public Gig () { }

    public Gig(String name, LocalDate date, String time, String address, String image) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
