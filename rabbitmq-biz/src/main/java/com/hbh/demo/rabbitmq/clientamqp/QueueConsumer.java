package com.hbh.demo.rabbitmq.clientamqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueueConsumer extends EndPoint implements Runnable,Consumer{
    public QueueConsumer(String endpointName) throws IOException {
        super(endpointName);
    }

    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }

    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
        String jsonStr = new String(body, CharEncoding.UTF_8);
        System.out.println("C收到消息:"+jsonStr);
        //        Map map = (HashMap) SerializationUtils.deserialize(body);
//        System.out.println("Message Number "+ map.get("message number") + " received.");
    }

    public void handleCancelOk(String s) { }
    public void handleCancel(String s) throws IOException { }
    public void handleShutdownSignal(String s, ShutdownSignalException e) { }
    public void handleRecoverOk(String s) { }
}
