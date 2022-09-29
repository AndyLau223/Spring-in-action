package demo.messagingwithredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class MessagingWithRedisApplication {

    private static final Logger logger =
            LoggerFactory.getLogger(MessagingWithRedisApplication.class);

    /**
     * Defines MessageListenerContainer
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    /**
     * Registers a message listener that's to be used in `container()` method. and will listen
     * to the `chat` topic.
     *
     * Because of `Receiver` class is a POJO, it needs to be wrapped in a message listener adapter
     * that implements the `MessageListener` interface(which is required by `addMessageListener()`)
     *
     * The message listener adapter is also configured to call the `receiveMessage()` method on `Receiver`
     * when a message arrives.
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * customized Receiver
     * @return
     */
    @Bean
    Receiver receiver(){
        return new Receiver();
    }

    /**
     * `StringRedisTemplate` is an implementation of `RedisTemplate` that is focused on the
     * common use of Reids, where both keys and values are `String` instances.
     * @param connectionFactory
     * @return
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(MessagingWithRedisApplication.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        while (receiver.getCount() == 0) {

            logger.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
            Thread.sleep(500L);
        }

        System.exit(0);
    }

}
