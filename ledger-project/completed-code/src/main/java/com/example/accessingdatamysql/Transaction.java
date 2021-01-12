package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Transaction {
    @Id // Sets member as primary key
    @GeneratedValue(strategy=GenerationType.AUTO) // Automatically populates value and auto-increments
    private Integer id;

    private String sender;

    private String recipient;

    private Boolean soft_delete;

    private Double transaction_value;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public Boolean getSoft_delete() { return soft_delete; }
    public void setSoft_delete(Boolean soft_delete) { this.soft_delete = soft_delete; }

    public Double getTransaction_value() { return transaction_value; }
    public void setTransaction_value(Double transaction_value) { this.transaction_value = transaction_value; }

}