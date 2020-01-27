package com.ps.netty.iso8583.services.models;

import java.io.Serializable;

public class Response implements Serializable {

    private String status;
    private String referenceNumber;


    public Response(String status, String referenceNumber) {
        this.status = status;
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }
}
