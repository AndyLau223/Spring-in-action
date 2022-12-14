package com.example.messagingwithrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingWithRabbitmqApplication {

     static final String topicExchangeName ="spring-boot-exchange";
     static final String queueName = "spring-boot";

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean TopicExchange exchange(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        return new SimpleMessageListenerContainerBuilder()
                .getInstance()
                .withConnectionFactory(connectionFactory)
                .withQueueName(queueName)
                .withMessageListener(listenerAdapter)
                .builder();
    }

    @Bean MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessagingWithRabbitmqApplication.class, args).close();
    }

}
