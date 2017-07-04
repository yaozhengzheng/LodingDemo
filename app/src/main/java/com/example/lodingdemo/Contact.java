package com.example.lodingdemo;

import android.graphics.Bitmap;

/**
 * Created on 2017/6/29.
 * author ${yao}.
 */

public class Contact {
    private String name;
    private String number;
    private String email;
    private Bitmap bitmap;
    private String sortkey;

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", bitmap=" + bitmap +
                ", sortkey='" + sortkey + '\'' +
                '}';
    }

    public String getSortkey() {
        return sortkey;
    }

    public void setSortkey(String sortkey) {
        this.sortkey = sortkey;
    }

    public Contact(String name, String number, String email, Bitmap bitmap, String sortkey) {
        this.name = name;
        this.number = number;
        this.bitmap = bitmap;
        this.email = email;
        this.sortkey = sortkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
