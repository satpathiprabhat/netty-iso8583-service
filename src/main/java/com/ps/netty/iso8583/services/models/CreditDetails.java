package com.ps.netty.iso8583.services.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreditDetails implements Serializable {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("creditAccountNumber")
    private String creditAccountNumber;
    @JsonProperty("mmid")
    private String mmid;
    @JsonProperty("mobileNumber")
    private String mobileNumber;
    @JsonProperty("ifscCode")
    private String ifscCode;
    @JsonProperty("name")
    private String name;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getMmid() {
        return mmid;
    }

    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreditDetails{" +
                "customerId='" + customerId + '\'' +
                ", creditAccountNumber='" + creditAccountNumber + '\'' +
                ", mmid='" + mmid + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
