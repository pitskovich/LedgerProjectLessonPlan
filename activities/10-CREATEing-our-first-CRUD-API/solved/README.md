# CREATEing our first CRUD API
## Overview
- In this activity, we will be adding code to the first method stub inside of MainController.java (addNewTransaction) to develop our first CRUD API. This method will be a POST request that will allow us to submit transactions into the ledger.

## Instructions:
- Begin by uncommenting the method signature for the "addNewTransaction" method from the MainController.java file. 
- Create a new Transaction object to hold our input parameters and ultimately write to the database
	- Transaction t = new Transaction();
- Call the setter methods on our newly instantiated object to map the input parameters to this object's private member fields
	- t.setSender(sender);  
t.setRecipient(recipient);  
t.setTransaction_value(transaction_value);  
t.setSoft_delete(false);
- Save the newly populated Transaction object to our MySQL database via the Transaction Repository interface
	- transactionRepository.save(t);
- Return a success message to indicate to the user that their transaction was saved
	- return "Transaction Successfully Saved";
