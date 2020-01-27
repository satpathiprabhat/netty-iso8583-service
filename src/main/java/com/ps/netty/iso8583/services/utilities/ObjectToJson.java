package com.ps.netty.iso8583.services.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.netty.iso8583.services.models.CreditDetails;
import com.ps.netty.iso8583.services.models.DebitDetail;
import com.ps.netty.iso8583.services.models.ImpsTransactionReq;

/**
 * @project netty-iso8583-services
 * @Aouthor PRABHAT on 26-01-2020.
 */
public class ObjectToJson {

    /*public static void main(String[] args) {

        ImpsTransactionReq request = new ImpsTransactionReq();
        request = getObjectData(request);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(request);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }*/

    public static ImpsTransactionReq getObjectData(ImpsTransactionReq request)
    {
        /**remitter details**/
        DebitDetail debitDetail = new DebitDetail();
        debitDetail.setCustomerId("106000000091");
        debitDetail.setDebitAccountNumber("50180000001114");
        debitDetail.setMmid("123456");
        debitDetail.setMobileNumber("7987541721");
        debitDetail.setName("prabhat satpathi");
        /**payee details**/
        CreditDetails creditDetails = new CreditDetails();
        creditDetails.setCustomerId("909010043780236");
        creditDetails.setCreditAccountNumber("123456789786");
        creditDetails.setMmid("123456");
        creditDetails.setMobileNumber("9987645755");
        creditDetails.setIfscCode("ICIC000047");
        creditDetails.setName("sapna satpathi");
        // Insert the data
       request.setDebitDetail(debitDetail);
       request.setCreditDetails(creditDetails);
       request.setAmount(103.0);
       request.setTranferType("P2A");
       request.setChannelId("MOB");
       request.setComments("Testing transaction");

        // Return the object
        return request;
    }
}
