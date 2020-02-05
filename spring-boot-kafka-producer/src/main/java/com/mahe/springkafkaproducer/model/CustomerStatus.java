package com.mahe.springkafkaproducer.model;

public enum CustomerStatus {

		/*RESTORED("Restored", "R"), 
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
	    }*/

	   
			RESTORED("R"), 
			SUSPENDED("S"),
			OPEN("O"), 
			CLOSED("C");

	        private String value;

	        CustomerStatus(String value) { this.value = value; }    

	        public String getValue() { return value; }

	        public static CustomerStatus parse(String id) {
	        	CustomerStatus right = null; // Default
	            for (CustomerStatus item : CustomerStatus.values()) {
	                if (item.getValue()==id) {
	                    right = item;
	                    break;
	                }
	            }
	            return right;
	        }



}
