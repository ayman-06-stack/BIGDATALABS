package edu.supmti.kafka;

import java.util.Properties;
import java.util.Scanner;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class WordProducer {
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            System.out.println("Entrer le nom du topic");
            return;
        }
        
        String topicName = args[0].toString();
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Entrez du texte (tapez 'exit' pour quitter) :");
        
        while (true) {
            String line = scanner.nextLine();
            
            if ("exit".equalsIgnoreCase(line.trim())) {
                break;
            }
            
            // Envoyer chaque mot séparément
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    producer.send(new ProducerRecord<String, String>(topicName, word, word));
                    System.out.println("Envoyé: " + word);
                }
            }
        }
        
        producer.close();
        scanner.close();
        System.out.println("Producer fermé.");
    }
}