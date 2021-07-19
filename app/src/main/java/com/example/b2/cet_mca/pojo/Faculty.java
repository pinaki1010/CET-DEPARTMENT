package com.example.b2.cet_mca.pojo;

public class Faculty {
    String name,post,image,mail,qualification,subject,phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Faculty() {

    }

    public Faculty(String name, String post, String image, String mail, String qualification, String subject, String phone) {

        this.name = name;
        this.post = post;
        this.image = image;
        this.mail = mail;
        this.qualification = qualification;
        this.subject = subject;
        this.phone = phone;
    }
}
