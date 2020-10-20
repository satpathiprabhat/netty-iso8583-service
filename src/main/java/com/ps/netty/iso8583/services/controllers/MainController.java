package com.ps.netty.iso8583.services.controllers;

import com.ps.netty.iso8583.services.handler.IsoMessageHandler;
import com.ps.netty.iso8583.services.models.Customer;
import com.ps.netty.iso8583.services.models.ImpsTransactionReq;
import com.ps.netty.iso8583.services.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    IsoMessageHandler msgHandler;
    @RequestMapping(value = "/findbyid",method = RequestMethod.GET)
    public Customer findById(@RequestParam String id){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("Prabhat");
        customer.setAge(32);
        customer.setAddress("Bangalore");

        return customer;
    }

    @RequestMapping(value = "/initiate/transation",method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Response> initiateTransaction(@RequestBody ImpsTransactionReq request){

        logger.info("Request: >>>>>>> {}",request.toString());
        Response response = new Response("202", UUID.randomUUID().toString().toUpperCase());
        msgHandler.handleRequest(request);
        return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
    }
    
    
    @RequestMapping(value = "/health",method = RequestMethod.GET)
    public String healthCheck(){
        return "service alive";
    }
}
