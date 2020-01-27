package com.ps.netty.iso8583.services.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DebitDetail implements Serializable {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("debitAccountNumber")
    private String debitAccountNumber;
    @JsonProperty("mmid")
    private String mmid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mobileNumber")
    private String mobileNumber;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getMmid() {
        return mmid;
    }

    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "DebitDetail{" +
                "customerId='" + customerId + '\'' +
                ", debitAccountNumber='" + debitAccountNumber + '\'' +
                ", mmid='" + mmid + '\'' +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
