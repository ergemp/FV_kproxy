package org.ergemp.fv.kproxy.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class GetKafkaProducer {
    public static Producer get (String gClientId){
        Producer producer = null;
        try {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.BOOTSTRAP_SERVERS_CONFIG);
            props.put(ProducerConfig.CLIENT_ID_CONFIG, gClientId);  //client.id
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaConfig.KEY_SERIALIZER_CLASS_CONFIG);  //key.serializer
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaConfig.VALUE_SERIALIZER_CLASS_CONFIG);  //value.serializer

            props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 5000);
            props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 5000);

            // safe producer settings
            props.put(ProducerConfig.ACKS_CONFIG, "ALL"); //default 1
            props.put(ProducerConfig.RETRIES_CONFIG, String.valueOf(Integer.MAX_VALUE));
            props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG , "100");
            props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
            props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

            producer = new KafkaProducer<String, String>(props);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            return producer;
        }
    }
}
