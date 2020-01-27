package com.ps.netty.iso8583.services.handler;

import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageListener implements IsoMessageListener<IsoMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    public MessageListener() {
        LOGGER.info("Listener Up and Listening >>>>>>>>>>>>>>");
    }

    @Override
    public boolean applies(IsoMessage isoMessage) {
        return true;
    }

    @Override
    public boolean onMessage(ChannelHandlerContext ctx, IsoMessage msg) {

        if (msg.getType()==0x210){
            LOGGER.info("Financial Message Received: [{}]",msg.debugString());
        }else if (msg.getType()==0x800){
            LOGGER.info("Echo Message Received: [{}]",msg.debugString());
        }

        return true;
    }
}
