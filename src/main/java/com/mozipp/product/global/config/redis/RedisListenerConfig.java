package com.mozipp.product.global.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {

    private final PortfolioResponseListener portfolioResponseListener;

    public RedisListenerConfig(PortfolioResponseListener portfolioResponseListener) {
        this.portfolioResponseListener = portfolioResponseListener;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(LettuceConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // PORTFOLIO_CREATION_SUCCESS 채널 구독
        container.addMessageListener(
                new MessageListenerAdapter(portfolioResponseListener, "handlePortfolioCreationSuccess"),
                new PatternTopic("PORTFOLIO_CREATION_SUCCESS")
        );

        // PORTFOLIO_CREATION_FAIL 채널 구독
        container.addMessageListener(
                new MessageListenerAdapter(portfolioResponseListener, "handlePortfolioCreationFail"),
                new PatternTopic("PORTFOLIO_CREATION_FAIL")
        );

        return container;
    }
}