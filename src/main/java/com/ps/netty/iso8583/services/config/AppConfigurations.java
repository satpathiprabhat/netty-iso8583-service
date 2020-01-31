package com.ps.netty.iso8583.services.config;

import com.github.kpavlov.jreactive8583.client.ClientConfiguration;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.ps.netty.iso8583.services.handler.IsoMessageHandler;
import com.ps.netty.iso8583.services.handler.MessageListener;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.impl.SimpleTraceGenerator;
import com.solab.iso8583.parse.ConfigParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

@Configuration
public class AppConfigurations {

    @Bean
    public Iso8583Client<IsoMessage> isoclient() throws InterruptedException {
        MessageFactory<IsoMessage> mf = null;
        Iso8583Client<IsoMessage> client = null;
        SocketAddress socketAddress = new InetSocketAddress("localhost",9050);
        ClientConfiguration clientConfigs = ClientConfiguration.newBuilder()
                .logSensitiveData(true)
                .idleTimeout(180)
                .reconnectInterval(10)
                .addLoggingHandler(true)
                .build();
        try {
            mf = createMessageFactory();
            client = new Iso8583Client<>(socketAddress,clientConfigs,mf);
            client.init();
            client.connectAsync();
            if (client.isConnected()){
                System.out.println("Client is connected to: "+client.getSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return client;
    }
    @Bean
    public MessageListener listener(){
        return new MessageListener();
    }

    private MessageFactory<IsoMessage> createMessageFactory() throws IOException {
        MessageFactory<IsoMessage> mf = ConfigParser.createFromClasspathConfig("j8583.xml");
        mf.setCharacterEncoding(StandardCharsets.UTF_8.name());
        mf.setForceSecondaryBitmap(true);
        mf.setUseBinaryBitmap(true);
        mf.setAssignDate(true);
        mf.setTraceNumberGenerator(new SimpleTraceGenerator((int) (System.currentTimeMillis() % 100000)));
        return mf;
    }

}
