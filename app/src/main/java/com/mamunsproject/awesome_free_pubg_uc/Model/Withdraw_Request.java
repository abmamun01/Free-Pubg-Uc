package com.mamunsproject.awesome_free_pubg_uc.Model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Withdraw_Request {

    @ServerTimestamp
    private Date createdAt;

    private String userId;
    private String PubgId;
    private String requestedBy;
    private long currentCoins;
    private String demand;
    private String name;

    public long getCurrentCoins() {
        return currentCoins;
    }

    public void setCurrentCoins(long currentCoins) {
        this.currentCoins = currentCoins;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Withdraw_Request(String userId, String PubgId, String requestedBy, long currentCoins, String demand, String name) {
        this.createdAt = createdAt;
        this.userId = userId;
        this.PubgId = PubgId;
        this.requestedBy = requestedBy;
        this.currentCoins=currentCoins;
        this.demand=demand;
        this.name=name;

    }

    public Withdraw_Request() {
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPubgId() {
        return PubgId;
    }

    public void setPubgId(String PubgId) {
        this.PubgId = PubgId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}

