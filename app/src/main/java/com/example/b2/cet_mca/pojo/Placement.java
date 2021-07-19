package com.example.b2.cet_mca.pojo;

public class Placement {
    String name,batch,company;

    public Placement(String name, String batch, String company) {
        this.name = name;
        this.batch = batch;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Placement() {

    }
}
