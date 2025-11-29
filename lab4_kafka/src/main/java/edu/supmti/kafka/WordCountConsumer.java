package edu.supmti.kafka;

import java.util.Properties;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class WordCountConsumer {
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            System.out.println("Entrer le nom du topic");
            return;
        }
        
        String topicName = args[0].toString();
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "wordcount-consumer-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topicName));
        
        System.out.println("Souscris au topic " + topicName);
        System.out.println("Comptage des mots en temps réel...\n");
        
        Map<String, Integer> wordCount = new HashMap<>();
        
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            
            for (ConsumerRecord<String, String> record : records) {
                String word = record.value().toLowerCase();
                
                // Mettre à jour le compteur
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                
                // Afficher le résultat mis à jour
                System.out.println("\n=== Statistiques actuelles ===");
                wordCount.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach(entry -> 
                        System.out.println(entry.getKey() + ": " + entry.getValue())
                    );
                System.out.println("=============================\n");
            }
        }
    }
}