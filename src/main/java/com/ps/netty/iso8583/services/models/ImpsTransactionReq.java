package com.ps.netty.iso8583.services.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImpsTransactionReq implements Serializable {


    @JsonProperty("debitDetail")
    private DebitDetail debitDetail;
    @JsonProperty("creditDetail")
    private CreditDetails creditDetails;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("transferType")
    private String tranferType;


    public DebitDetail getDebitDetail() {
        return debitDetail;
    }

    public void setDebitDetail(DebitDetail debitDetail) {
        this.debitDetail = debitDetail;
    }

    public CreditDetails getCreditDetails() {
        return creditDetails;
    }

    public void setCreditDetails(CreditDetails creditDetails) {
        this.creditDetails = creditDetails;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTranferType() {
        return tranferType;
    }

    public void setTranferType(String tranferType) {
        this.tranferType = tranferType;
    }

    @Override
    public String toString() {
        return "ImpsTransactionReq{" +
                "debitDetail=" + debitDetail +
                ", creditDetails=" + creditDetails +
                ", channelId='" + channelId + '\'' +
                ", comments='" + comments + '\'' +
                ", amount=" + amount +
                ", tranferType='" + tranferType + '\'' +
                '}';
    }
}
