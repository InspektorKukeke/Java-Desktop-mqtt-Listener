package com.mycompany.mqttListener;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


//some of this code is borrowed from in-class mqtt example
//Due to most basic nature of this app, there wasn't anything else to add

public class Connector {

    public static void main(String[] args) {

        final String clientId = MqttClient.generateClientId();
        MemoryPersistence persistence = new MemoryPersistence();
        final String valueTopic = "sensors/value/";
        final String snsTemp = "temp";
        final String snsHum = "hum";
        final String snsLed = "led";
        final String snsMotsen = "motsen";
        final String snsLcd = "lcd";
        final String topicErrorOut = "sensors/error/outbound/";
        final String topicErrorIn = "sensors/error/inbound";
        final String broker = "tcp://m24.cloudmqtt.com:17643";
        final String USER = "cvhjodxm";
        final String PW = "noQYPCm8bCZm";

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(USER);
            connOpts.setPassword(PW.toCharArray());            
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            sampleClient.setCallback(new Listener());
            sampleClient.subscribe(topicErrorOut+snsTemp);
            sampleClient.subscribe(topicErrorOut+snsTemp);
            sampleClient.subscribe(topicErrorOut+snsTemp);
            sampleClient.subscribe(topicErrorOut+snsHum);
            sampleClient.subscribe(topicErrorOut+snsLed);
            sampleClient.subscribe(topicErrorOut+snsMotsen);
            sampleClient.subscribe(topicErrorOut+snsLcd);
            sampleClient.subscribe(valueTopic+snsTemp);
            sampleClient.subscribe(valueTopic+snsHum);
            sampleClient.subscribe(valueTopic+snsLed);
            sampleClient.subscribe(valueTopic+snsMotsen);
            sampleClient.subscribe(valueTopic+snsLcd);
            sampleClient.subscribe(topicErrorIn);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
