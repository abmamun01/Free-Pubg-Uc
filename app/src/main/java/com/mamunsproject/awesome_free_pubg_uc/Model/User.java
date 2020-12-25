package com.mamunsproject.awesome_free_pubg_uc.Model;

public class User {

    private String name;
    private String email;
    private String pass;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    private String profile;
    private String PubgCode;
    private long coins;

    public User() {
    }


    public User(String name, String email, String pass, String freeFireCode) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.PubgCode = PubgCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRefercode() {
        return PubgCode;
    }

    public void setRefercode(String refercode) {
        this.PubgCode = refercode;
    }
    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

}
