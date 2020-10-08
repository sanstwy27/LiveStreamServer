package com.sanstwy27.livechatserver.config;

import com.sanstwy27.livechatserver.service.HandshakeInterceptor;
import com.sanstwy27.livechatserver.service.MyChannelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Bean
    public MyChannelInterceptor myChannelInterceptor() {
        return new MyChannelInterceptor();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // CORS
        // setAllowedOrigins(<URL>)
        registry.addEndpoint("/live").setAllowedOrigins("*")
                .addInterceptors(new HandshakeInterceptor()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Direct to register
        registry.enableSimpleBroker("/topic","/queue");

        // To rabbitMQ, activeMq, etc.
        /*
        registry.setApplicationDestinationPrefixes("/demo")
                .enableStompBrokerRelay("/topic", "/queue")
                .setRelayHost("127.0.0.1")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest")
                .setSystemLogin("guest")
                .setSystemPasscode("guest")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);
         */
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(myChannelInterceptor());
        WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        WebSocketMessageBrokerConfigurer.super.configureClientOutboundChannel(registration);
    }
}
