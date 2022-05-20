package org.loose.fis.model;

import java.sql.Date;

public class appointmentsRestaurant {

    int id;
    String client;
    Date data;
    String ora;


    public appointmentsRestaurant(int id, String client, Date data, String ora) {
        this.id = id;
        this.client = client;
        this.data = data;
        this.ora = ora;

    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

