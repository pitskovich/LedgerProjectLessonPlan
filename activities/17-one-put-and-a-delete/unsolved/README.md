# One PUT and a DELETE
## Overview
- In this activity, we will be implementing the final 2 API Endpoints in our MainController.java file, updateTransactionValue and softDeleteTransaction. 
	- updateTransactionValue - PUT Endpoint that updates the transaction_value of a specific transaction determined by transaction id
	- softDeleteTransaction - DELETE Endpoint that "soft-deletes" a transaction

## Instructions:
- Create a collection of type Optional\<Transaction\> to hold a reference to the transaction of specified transaction id
- Check if the referenced transaction is null, if not, update the transaction value and save to the database

- Next, let's move on to the DELETE API...
- Begin with the code you wrote for the PUT API, this method will be similar in nature. If the transaction specified by the input id is not null, the soft_delete flag should be switched to true. Then, save to the database and return a success message.
