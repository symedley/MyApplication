package com.example.susannah.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by Susannah on 2/18/2018.
 */

@Entity (indices = {@Index(value = {"firstname", "lastname"},
        unique = true)})
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstname;

    @ColumnInfo(name = "last_name")
    public String lastname;

    public int age;

    // Embed an object in another object in the database.
    @Embedded
    public Address address;

    @Ignore
    private Bitmap picture;
}
