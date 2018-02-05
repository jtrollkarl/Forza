package com.moducode.forzateams.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jay on 2018-02-02.
 */

public class Team {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("national")
    @Expose
    private Boolean national;

    @SerializedName("country_name")
    @Expose
    private String countryName;


    public Team(String name, Boolean national, String countryName) {
        this.name = name;
        this.national = national;
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNational() {
        return national;
    }

    public void setNational(Boolean national) {
        this.national = national;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
