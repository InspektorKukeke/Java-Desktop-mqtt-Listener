package com.mycompany.mqttListener;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

//Some of this code is made on in-class example

class Listener implements MqttCallback {

    public Listener() {
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
    }

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        switch (string) {
            case ("sensors/value/led"):
                if (mm.toString().equals("0")) {
                    print("LED is OFF");
                } else {
                    print("LED is ON");
                }
                break;
            case ("sensors/value/temp"):
                if (mm.toString().equals("...")) {
                    print("TEMP is OFF");
                } else {
                    print("TEMP is " + mm + " C");
                }
                break;
            case ("sensors/value/hum"):
                if (mm.toString().equals("...")) {
                    print("HUM is OFF");
                } else {
                    print("HUM is " + mm + " %");
                }
                break;
            case ("sensors/value/motsen"):
                if (mm.toString().equals("...")) {
                    print("ULTRASONIC is OFF");
                } else {
                    print("ULTRASONIC reading is " + mm + " cm");
                }
                break;
            case ("sensors/value/lcd"):
                if (mm.toString().equals("1")) {
                    print("LCD has finished printing!");
                }
                break;
            case ("sensors/error/inbound"):
                print("ERROR!! ISSUE WITH INSTRUCTIONS: " + mm);
                break;
            case ("sensors/error/outbound/temp"):
                print("ERROR!! TEMP SESNOR ISSUE: " + mm);
                break;
            case ("sensors/error/outbound/hum"):
                print("ERROR!! HUMIDITY SESNOR ISSUE: " + mm);
                break;
            case ("sensors/error/outbound/motsen"):
                print("ERROR!! ULTRASONIC SESNOR ISSUE: " + mm);
                break;
            default:
                print("SOMETHING WENT WRONG!!!");
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {

    }

    public void print(String s) {
        System.out.println(s);
    }

}
