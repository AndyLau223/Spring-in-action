package com.example.messagingwithrabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class SimpleMessageListenerContainerBuilder {

    private SimpleMessageListenerContainer container;

    public SimpleMessageListenerContainerBuilder(){}

    public SimpleMessageListenerContainerBuilder getInstance(){
        container =  new SimpleMessageListenerContainer();
        return this;
    }


    public SimpleMessageListenerContainerBuilder withConnectionFactory(ConnectionFactory connectionFactory) {
        container.setConnectionFactory(connectionFactory);
        return this;
    }

    public SimpleMessageListenerContainerBuilder withQueueName(String queueName) {
        container.setQueueNames(queueName);
        return this;
    }

    public SimpleMessageListenerContainerBuilder withMessageListener(MessageListenerAdapter listenerAdapter) {
        container.setMessageListener(listenerAdapter);
        return this;
    }

    public SimpleMessageListenerContainer builder() {
        return container;
    }

}
