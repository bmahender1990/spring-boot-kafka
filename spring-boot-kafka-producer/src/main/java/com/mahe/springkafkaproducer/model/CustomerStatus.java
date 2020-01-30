package com.mahe.springkafkaproducer.model;

public enum CustomerStatus {

		RESTORED("Restored", "R"), 
		SUSPENDED("Suspended", "S"),
		OPEN("Open", "O"), 
		CLOSED("Closed", "C");

	    private final String key;
	    private final String value;

	   CustomerStatus(String key, String value) {
	        this.key = key;
	        this.value = value;
	    }

	    public String getKey() {
	        return key;
	    }
	    public String getValue() {
	        return value;
	    }

}
