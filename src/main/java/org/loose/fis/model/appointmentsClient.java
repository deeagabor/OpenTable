package org.loose.fis.model;

import java.sql.Date;

public class appointmentsClient {

    int id;
    String restaurant;
    String plates;
    Date data;



    String ora;


    public appointmentsClient(int id, String restaurant, Date data,String ora) {
        this.id = id;
        this.restaurant = restaurant;
        this.data = data;
        this.ora = ora;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

