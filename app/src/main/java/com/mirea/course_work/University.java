package com.mirea.course_work;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class University {
    @PrimaryKey
    @NonNull
    public String name;
    public boolean isGov;
    public boolean haveDorm;
    public String city;
    public int image; //TODO id drawable

    public University(String name, boolean isGov, boolean haveDorm, String city, int image) {
        this.name = name;
        this.isGov = isGov;
        this.haveDorm = haveDorm;
        this.city = city;
        this.image = image;
    }
}
