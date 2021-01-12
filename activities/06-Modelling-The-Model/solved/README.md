# Modelling the Model
## Overview
- In this activity, we will be creating the model class for our Ledger Project (Transaction.java)

## Instructions:
- Begin by cloning the starter code from the GitLab URL, as provided by your instructional staff, and import the project into IntelliJ.
- Under "../src/main/java/com.example.accessingdatamysql", create a new class named Transaction.java
- Add the following private members to the Transaction class:
	- id - Integer
	- sender - String
	- recipient - String
	- soft_delete - Boolean
	- transaction_value - Double
		-	private Integer id;  
  private String sender;  
private String recipient;  
private Boolean soft_delete;  
private Double transaction_value;
- Create a getter and setter method for each private member. Not all of these methods will be used in the Ledger Project, but you may choose to use these in the future for further enhancements
	- public Integer getId() { return id; }  
public void setId(Integer id) { this.id = id; }  
public String getSender() { return sender; }  
public void setSender(String sender) { this.sender = sender; }  
public String getRecipient() { return recipient; }  
public void setRecipient(String recipient) { this.recipient = recipient; }  
public Boolean getSoft_delete() { return soft_delete; }  
public void setSoft_delete(Boolean soft_delete) { this.soft_delete = soft_delete; }  
public Double getTransaction_value() { return transaction_value; }  
public void setTransaction_value(Double transaction_value) { this.transaction_value = transaction_value; }
