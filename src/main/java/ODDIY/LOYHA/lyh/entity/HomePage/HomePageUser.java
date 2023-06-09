package ODDIY.LOYHA.lyh.entity.HomePage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HomePageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;

    private String name;
    private String surname;
    private String whoIs;

    private String email;

    private String number;
    private String t_me_address;
    private String picture_user_hashId;
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getWhoIs() {
        return whoIs;
    }

    public void setWhoIs(String whoIs) {
        this.whoIs = whoIs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getT_me_address() {
        return t_me_address;
    }

    public void setT_me_address(String t_me_address) {
        this.t_me_address = t_me_address;
    }

    public String getPicture_user_hashId() {
        return picture_user_hashId;
    }

    public void setPicture_user_hashId(String picture_user_hashId) {
        this.picture_user_hashId = picture_user_hashId;
    }
}
