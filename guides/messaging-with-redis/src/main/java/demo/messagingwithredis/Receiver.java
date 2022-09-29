package demo.messagingwithredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This POJO defines a method for receiving messages. When you register the `Receiver`
 * as a message listener, you can name the message-handling method whatever you want.
 */
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount(){
        return counter.get();
    }
}
