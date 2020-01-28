package com.mahe.springkafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	 @Value("${kafka.bootstrap-server}")
	    private String server;
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, Object>(config);
	}
		
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,Object> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,Object> factory= new ConcurrentKafkaListenerContainerFactory<String,Object>(); 
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	
	
	
/*	@Bean
	public ConsumerFactory<String, Customer> CustomerConsumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<String, Customer>(config,new StringDeserializer(), new JsonDeserializer<>(Customer.class));
	}
	

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, Customer> customerkafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, Customer> factory =        new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }*/
	    
//	    @Bean
//	    public ConsumerFactory<String, Car> consumerFactory() {
//	      return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
//	          new JsonDeserializer<>(Car.class));
//	    }
//
//	    @Bean
//	    public ConcurrentKafkaListenerContainerFactory<String, Car> kafkaListenerContainerFactory() {
//	      ConcurrentKafkaListenerContainerFactory<String, Car> factory =
//	          new ConcurrentKafkaListenerContainerFactory<>();
//	      factory.setConsumerFactory(consumerFactory());
//
//	      return factory;
//	    }
	
}
