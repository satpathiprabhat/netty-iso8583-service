package com.ps.netty.iso8583.services.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class PropertyResolver {

    private String applicationName;
    private String cardAcceptorTerminalId;
    private String cardAcceptorName;
    private String remmiterNbin;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getCardAcceptorTerminalId() {
        return cardAcceptorTerminalId;
    }

    public void setCardAcceptorTerminalId(String cardAcceptorTerminalId) {
        this.cardAcceptorTerminalId = cardAcceptorTerminalId;
    }

    public String getCardAcceptorName() {
        return cardAcceptorName;
    }

    public void setCardAcceptorName(String cardAcceptorName) {
        this.cardAcceptorName = cardAcceptorName;
    }

    public String getRemmiterNbin() {
        return remmiterNbin;
    }

    public void setRemmiterNbin(String remmiterNbin) {
        this.remmiterNbin = remmiterNbin;
    }

    @Override
    public String toString() {
        return "PropertyResolver{" +
                "applicationName='" + applicationName + '\'' +
                ", cardAcceptorTerminalId='" + cardAcceptorTerminalId + '\'' +
                ", cardAcceptorName='" + cardAcceptorName + '\'' +
                ", remmiterNbin='" + remmiterNbin + '\'' +
                '}';
    }
}
