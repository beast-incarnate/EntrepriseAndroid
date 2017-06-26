package com.example.kunalsingh.entreprise.models;

/**
 * Created by kunalsingh on 24/06/17.
 */

public class Host {

    private String hostName;
    private int id;
    public Host(String hostName , int id) {
        this.hostName = hostName;
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
