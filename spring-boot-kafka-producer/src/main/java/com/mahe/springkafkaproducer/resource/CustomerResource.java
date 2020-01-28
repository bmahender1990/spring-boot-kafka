package com.mahe.springkafkaproducer.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahe.springkafkaproducer.model.Customer;

@RestController
@RequestMapping("kafka")
public class CustomerResource {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);
	@Autowired
	KafkaTemplate<String, Customer> kafkaTemplate;
	
	@Value("${app.topic}")
    private String topic;
	
	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public String post(@RequestBody Customer customer) {
		
		LOG.info("sending data='{}' to topic='{}'", customer, topic);		
		kafkaTemplate.send(topic,customer);
		
		return "successfully published";		
	}

}
