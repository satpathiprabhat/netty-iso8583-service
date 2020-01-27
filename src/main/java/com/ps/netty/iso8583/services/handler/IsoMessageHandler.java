package com.ps.netty.iso8583.services.handler;

import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.ps.netty.iso8583.services.models.ImpsTransactionReq;
import com.ps.netty.iso8583.services.utilities.IsoMessageBuilder;
import com.solab.iso8583.IsoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ps.netty.iso8583.services.constants.IsoMsgConstants.DE120;
import static com.ps.netty.iso8583.services.constants.IsoMsgConstants.DE37;

@Component
public class IsoMessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsoMessageHandler.class);

    Iso8583Client<IsoMessage> isoClient;
    @Autowired
    IsoMessageBuilder messageBuilder;

    @Autowired
    public IsoMessageHandler(Iso8583Client<IsoMessage> isoClient) {
        this.isoClient = isoClient;
    }

    public void handleRequest(ImpsTransactionReq request){
        IsoMessage or_msg = messageBuilder.buildORMsg(request);
        String referenceId = or_msg.getObjectValue(DE37);

        if (isoClient.isConnected()){
            LOGGER.info("RRN: [{}] - ORIGINAL MESSAGE: [{}]",referenceId,or_msg.getObjectValue(DE120));
            isoClient.sendAsync(or_msg);
        }

    }

}
