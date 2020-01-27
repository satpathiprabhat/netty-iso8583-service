package com.ps.netty.iso8583.services.utilities;

/**
 * @project netty-iso8583-services
 * @Aouthor PRABHAT on 25-01-2020.
 */
public class DataElement {

    private String id;
    private String value;

    public DataElement(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
