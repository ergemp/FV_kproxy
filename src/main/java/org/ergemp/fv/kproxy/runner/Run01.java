package org.ergemp.fv.kproxy.runner;

import org.apache.kafka.clients.producer.Producer;
import org.ergemp.fv.kproxy.actor.SendMessage;
import org.ergemp.fv.kproxy.config.GetKafkaProducer;
import org.ergemp.fv.kproxy.model.GenericDataModel;

public class Run01 {

    public static void main(String[] args) {

        Producer producer = GetKafkaProducer.get("run01");
        SendMessage.send(producer, "topicName", new GenericDataModel(
                "f2d52d8e-26f3-40c1-910a-4c6a001f2589",
                        System.currentTimeMillis(),
                "localhost",
                "5" ).toString());

    }
}
