package com.mozipp.product.global.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisListenerConfig.class);

    private final PortfolioResponseListener portfolioResponseListener;

    public RedisListenerConfig(PortfolioResponseListener portfolioResponseListener) {
        this.portfolioResponseListener = portfolioResponseListener;
    }

    /**
     * MessageListenerAdapter for PORTFOLIO_CREATION_SUCCESS
     */
    @Bean
    public MessageListenerAdapter successListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(portfolioResponseListener, "handlePortfolioCreationSuccess");
        return adapter;
    }

    /**
     * MessageListenerAdapter for PORTFOLIO_CREATION_FAIL
     */
    @Bean
    public MessageListenerAdapter failListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(portfolioResponseListener, "handlePortfolioCreationFail");
        return adapter;
    }

    /**
     * RedisMessageListenerContainer Bean 등록
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            LettuceConnectionFactory connectionFactory,
            MessageListenerAdapter successListenerAdapter,
            MessageListenerAdapter failListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // MessageListenerAdapter와 채널 연결
        container.addMessageListener(successListenerAdapter, new PatternTopic("PORTFOLIO_CREATION_SUCCESS"));
        container.addMessageListener(failListenerAdapter, new PatternTopic("PORTFOLIO_CREATION_FAIL"));

        logger.info("RedisMessageListenerContainer initialized with successAdapter and failAdapter");
        return container;
    }
}