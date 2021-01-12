# Get, Get, GET!
## Overview
- In this activity, we will be implementing 3 GET API Endpoints to achieve the following:
	- Return a specific transaction by id
	- Return all transactions from the database that are not soft-deleted
	- Return the sum of all transaction values for transactions that are not soft-deleted

## Instructions:
- Begin by uncommenting the method stubs for "getById", "getAllUsers", and "getSumTransactions"
- GET API #1 - return transaction by id:
	- Add a return statement to the method stub that returns a collection of type "Optional\<Transaction\>" containing the transaction with specified id
	- Hint: See what methods can be invoked on the transaction repository
		- return transactionRepository.findById(id);
- GET API #2 - return all transactions that are not soft-deleted
	- Create a collection of type Iterable\<Transaction\> that contains all of the transactions in the database.
		- Iterable\<Transaction\> allTransactions = transactionRepository.findAll(); 
	- Create an empty collection to hold the transactions that have not been soft deleted
		- ArrayList\<Transaction\> availableTransactions = new ArrayList<>();
	- Loop through all of the transactions, and add the ones that have not been deleted to our empty collection, afterwards, return the populated collection
		- for (Transaction t: allTransactions)  
{  
    if(t.getSoft_delete() == false)  
    {  
        availableTransactions.add(t);  
  }  
}  
return availableTransactions;

- Get API #3 - return the sum of all transaction values for transactions that are not soft-deleted
	- Begin with the same code used to implement GET API #2, modify it to return the sum of all transactions that are not soft deleted
		- Iterable\<Transaction\> allTransactions = transactionRepository.findAll();  
Double sum = 0.0;  
			for (Transaction t: allTransactions)  
{  
    if(t.getSoft_delete() == false)  
    {  
        sum += t.getTransaction_value();  
  }  
}  
return sum;
