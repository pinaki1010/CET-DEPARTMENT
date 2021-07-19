package com.example.b2.cet_mca.pojo;

public class Syllabus {
    String name,pdf;

    public Syllabus(String name, String pdf) {
        this.name = name;
        this.pdf = pdf;
    }

    public Syllabus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
