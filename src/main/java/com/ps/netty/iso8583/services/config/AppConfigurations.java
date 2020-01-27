package com.ps.netty.iso8583.services.config;

import com.github.kpavlov.jreactive8583.client.ClientConfiguration;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.ps.netty.iso8583.services.handler.IsoMessageHandler;
import com.ps.netty.iso8583.services.handler.MessageListener;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Configuration
public class AppConfigurations {

    @Bean
    public Iso8583Client<IsoMessage> isoclient() throws InterruptedException {
        MessageFactory<IsoMessage> mf = null;
        Iso8583Client<IsoMessage> client = null;
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",9050);
        ClientConfiguration clientConfigs = ClientConfiguration.newBuilder()
                .logSensitiveData(true)
                .idleTimeout(10)
                .reconnectInterval(10)
                .addLoggingHandler(true)
                .build();
        try {
            mf = ConfigParser.createFromClasspathConfig("j8583.xml");
            client = new Iso8583Client<>(socketAddress,clientConfigs,mf);
            client.init();
            client.connect();
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

}
