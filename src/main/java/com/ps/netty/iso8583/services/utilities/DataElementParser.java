package com.ps.netty.iso8583.services.utilities;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @project netty-iso8583-services
 * @Aouthor PRABHAT on 25-01-2020.
 */
public class DataElementParser {

    static Map<String,String> DE_MAP = new LinkedHashMap<>();
    static int POSITION_0 = 0;
    static int POSITION_3 = 3;
    static int POSITION_6 = 6;
    static String LENGTH_PADDING ="0";


    public static String addElementAfter(String isoMsg,String findElement,DataElement newElement){
        return addElement(isoMsg,findElement,newElement);
    }

    private static String addElement(String isoMsg,String findElement,DataElement newElement){
        StringBuilder sb = new StringBuilder();
        Map<String,String> formatedDEMap = new TreeMap<>();

        while (isoMsg.length()>0){
            String fieldName = isoMsg.substring(POSITION_0,POSITION_3);
            String fieldLen = isoMsg.substring(POSITION_3,POSITION_6);
            String fieldValue = isoMsg.substring(POSITION_6,POSITION_6+Integer.valueOf(fieldLen));
            formatedDEMap.put(fieldName,fieldValue);
            isoMsg = isoMsg.substring(POSITION_6+Integer.valueOf(fieldLen),isoMsg.length());
        }

        for (String field : formatedDEMap.keySet()){
            String val = formatedDEMap.get(field);
            sb.append(field).append(getIsoLength(val.length())).append(val);
            if (findElement.equals(field)){
                sb.append(getIsoLength(newElement.getValue().length()))
                        .append(newElement.getValue());
            }
        }
        return sb.toString();
    }

    private static String getIsoLength(int newElementVal){
        return StringUtils.leftPad(String.valueOf(newElementVal),POSITION_3,"0");
    }

    private static String parse(String isoMsg){
        String fieldName = isoMsg.substring(POSITION_0,POSITION_3);
        String fieldLen = isoMsg.substring(POSITION_3,POSITION_6);
        String fieldValue = isoMsg.substring(POSITION_6,POSITION_6+Integer.valueOf(fieldLen));
        DE_MAP.put(fieldName,fieldValue);
        return isoMsg.substring(POSITION_6+Integer.valueOf(fieldLen),isoMsg.length());
    }

    public static Map<String,String> getDataElements(String isoMsg){
        Map<String,String> formatedDEMap = new TreeMap<>();
        while (isoMsg.length()>0){
            String fieldName = isoMsg.substring(POSITION_0,POSITION_3);
            String fieldLen = isoMsg.substring(POSITION_3,POSITION_6);
            String fieldValue = isoMsg.substring(POSITION_6,POSITION_6+Integer.valueOf(fieldLen));
            formatedDEMap.put(fieldName,fieldValue);
            isoMsg = isoMsg.substring(POSITION_6+Integer.valueOf(fieldLen),isoMsg.length());
        }
        return formatedDEMap;
    }
}
