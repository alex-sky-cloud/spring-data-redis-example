package redis.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.service.message.MessagePublisher;
import redis.service.message.impl.MessagePublisherImpl;
import redis.service.message.impl.MessageSubscriber;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.database}")
    private int indexDataBase;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName(this.hostName);
        /*Указываем номер индекса базы, который мы будем использовать,
        для подключения фабрики подключений.*/
        redisStandaloneConfiguration.setDatabase(this.indexDataBase);

        LettuceConnectionFactory lettuceConnectionFactory =
                new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;
    }

    /* RedisTemplate взаимодействует с базой Redis как наш backend,
    по отношению к нашему приложению*/
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        final RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(lettuceConnectionFactory());

        template.setKeySerializer(new GenericToStringSerializer<>(Object.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        return template;
    }

    /**
     * <b>MessageSubscriber</b> is  a implementation
     * of {@link org.springframework.data.redis.connection.MessageListener}
     * The MessageSubscriber is developed in the app
     * @return
     */
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageSubscriber());
    }


    @Bean
    RedisMessageListenerContainer redisContainer() {

        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    /**
     * <b>MessagePublisherImpl</b> is a implementation of {@link redis.service.message.MessagePublisher}
     * The MessagePublisher is developed in the application
     * @return
     */
    @Bean
    MessagePublisher redisPublisher() {

        return new MessagePublisherImpl(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}
