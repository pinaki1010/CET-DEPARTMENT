package com.example.b2.cet_mca.pojo;

public class Notice {
    String title,body,time,link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Notice() {

    }

    public Notice(String title, String body, String time, String link) {

        this.title = title;
        this.body = body;
        this.time = time;
        this.link = link;
    }
}
