# Lesson Plan: The Ledger Project

## Overview: 

In today's class, we will be guiding the students through the completion of the Ledger Project. This project is a simple system that acts as a ledger for financial transactions. The system consists of:
- A **Spring Boot Java API** back-end microservice
	- Standard CRUD service to perform create, read, update, and delete operations using **Spring Data JPA**
- A **MySQL** Database to store transactions 	

## Class Objectives:

The primary goal of this class is to use the Ledger Project demonstration to show that CRUD and REST work together. Additionally, the objectives of this class are to:
- Create sophisticated Controller endpoint routes to perform a variety of operations on transactions stored within the ledger
- Reinforce previously introduced back-end development fundamentals and concepts (Object-Oriented Programming, Spring MVC, RESTful APIs, and SQL)
- Provide students a simple-yet-sophisticated, "real-world" example demonstrating how CRUD and REST can form the backbone of a software application 

## Instructor Notes:
- Although we have worked with SQL in the past, remind students at the beginning of class that they will need to have MySQL installed on their development machines to complete the Ledger Project. 
	> **Instructor Tip:** Suggest working with a TA if a student is facing any technical difficulties in setting up their local instance of MySQL server and/or MySQL Workbench

- All of the necessary source files for completing the Ledger Project are found [here](ledger-project). Share the folder labelled [starter-code](ledger-project/starter-code) with students via GitLab. The [completed-code](ledger-project/completed-code) may be referenced during the Instructor Demos and provided to students after class. 

## Project Setup:
The following dependencies will be need to be installed to complete the Ledger Project, many of which overlap with dependencies from prior topics:
- MySQL version 5.6 or later
- MySQL Workbench
	> Downloads for these can be found at https://dev.mysql.com/downloads/. Instruct students to follow links for "MySQL Community Server" and "MySQL Workbench", as well as downloading the appropriate installer for their specified operating system. 
- JDK 1.8 or later
- Gradle 4+ or Maven 3.2+ (Maven preferred)
- IntelliJ IDEA IDE Community Edition
- Postman and/or cURL for API testing (student preference)   

## Slideshow:
- TODO: This is a placeholder for the lesson slides once they are available on Google Drive :smile:  
	-   To add slides to the student-facing repository, download the slides as a PDF by navigating to File > "Download as" and choose "PDF document." Then, add the PDF file to your class repository along with any other necessary files.
    
	>   **Note:**  Editing access is not available for this document. If you or your students wish to modify the slides, please create a copy by navigating to File > "Make a copy...".

## Time Tracker:
- [Time Tracker](Timetracker.csv)

## Student Guide:
- [Ledger Project Student Guide](studentguide.md)

### 01. Instructor Do: Welcome to the Ledger Project (0:10)
Begin class by welcoming the students and explaining that today's class will consist of an overview of CRUD operations. Explain that CRUD is used to modify data within an application's database, and that the concepts covered today will build upon previously taught concepts including RESTful APIs, SQL, Spring Data JPA, and Spring MVC. 

Before diving too deep into CRUD, briefly review some of the aforementioned topics as a refresher:
- A **RESTful API** is an architectural style for an application program interface (API) that uses HTTP requests to access and use data
- The requests can be used to **GET**, **PUT**, **POST** and **DELETE** data resources
- **SQL** stands for Structured Query Language. It is used to communicate with a database
- **Spring Data JPA** makes it easy to implement Java Persistence API based repositories. This module deals with enhanced support for JPA based data access layers
- **Spring Web model-view-controller** (MVC) framework 
	- **Model**: The central component of the pattern. It is the application's dynamic data structure, independent of the user interface
	- **View**: Any visual representation of information (i.e. Web GUI, Mobile App, etc.)
	- **Controller**: Accepts input and converts it to commands for the model or view

Introduce the Ledger Project by explaining that today we will be progressively building a Spring Boot Java API back-end microservice which can record financial transactions in our MySQL Databases. Mention that the Ledger Project is a good example of a "real-world" application that combines many of the individual concepts that we have learned over the prior weeks. Encourage students to engage with their fellow classmates or instructional support staff if issues arise during the activities to prevent potentially falling behind. 

### 02. Instructor Do: MySQL Setup Reminder (0:05)
Remind students that having MySQL server and MySQL Workbench installed on their development machines will be necessary to complete the Ledger Project during class. Although they should be keen to ensure that each of the project prerequisites are installed (the list is provided in the student guide), MySQL server will take the longest to install and now is a good time to kick-off the installation process. If students have further questions about this, refer them to the instructions in the student guide or to privately consult a TA.  

### 03. Instructor Do: Introduction to CRUD (0:15)
Begin by explaining that CRUD stands for: **create**, **read**, **update**, and **delete**. These operations are the four basic functions of persistent storage. CRUD is also sometimes used to describe user interface conventions that facilitate viewing, searching, and changing of information.

A little background on CRUD...
- The most fundamental feature of a database is that it has a readable and updatable state. These _read_ and _update_ operations are the two basic operations on a database (The 'RU' of 'CRUD')
- Before a storage location can be read or updated, it needs to be available. A storage location can be made either available or unavailable for usage. These _create_ and _delete_ operations are the two other basic operations on a database (The 'CD' of 'CRUD')

Explain to students that the CRUD operations on our database will be executed by the code that we write in our back-end RESTful APIs when they are invoked. This is the way in which REST works with CRUD to manage the data in our database. A few of the reasons that we let the REST API handle the CRUD operations include:
- Providing an extra layer of abstraction between the User Interface and the database - the underlying structure of the database tables becomes hidden
- The database is one of the most sensitive parts of any application. Allowing only the REST APIs to interact with it provides enhanced security
- REST APIs allow for better error handling than native SQL queries, as a developer we can validate and sanitize input parameters

||CRUD|REST|
|-|-|-|
||Create|POST|
||Read|GET|
||Update|PUT|
||Delete|DELETE|

Challenge the students to come up with these reasons (or any additional reasons) on their own - this will help form a deeper understanding and sense of purpose for the following activities.  

### 04. Instructor & Student Do: Prepping the DB (0:05)
Inform the students that in order for our application to communicate with our MySQL Server, we need to create a database and a user that our application can authenticate with. Instruct students to open MySQL Workbench and run the following query in tandem with the instructor:
- create database db_example; 
create user 'springuser'@'%' identified by 'ThePassword'; 
grant all on db_example.* to 'springuser'@'%';
	-	Students can change 'ThePassword' to be any password that they choose. If they choose to change it, inform them that they must make the same change in the application.properties file in their Spring project

application.properties provided for reference:
- spring.jpa.hibernate.ddl-auto=update  
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example  
spring.datasource.username=springuser  
spring.datasource.password=ThePassword

Now, we are ready to proceed

### 05. Instructor Do: Import Project & Dissect the Model (0:20)
>**Curriculum Writer Assumption:** Based on the initial outline for the Ledger Project, I am making the assumption that the students have some experience with Spring Data JPA & Maven projects, so the initial clone and import of the project should be a quick step  

Clone a local copy of the starter code for the Ledger Project from GitLab as a demonstration for the students. Although they should know how to do this step, a live demo may be beneficial in case anyone needs a refresher. Walk the students through importing the project into IntelliJ as a Maven project and ensure that all of the dependencies are indexed and downloaded. Briefly open the pom.xml file in IntelliJ and remind the students that this is where the dependencies to build the Spring Data JPA project are stored. 

Under "/src/main/java/com.example.accessingdatamysql", create a new class called "Transaction.java". Tell the students that we will begin by defining the "model" of our transaction object that will be stored in our database. Walk the students through creating the private members of the Transaction class. Start by giving the students the "id" member, and encourage students to think about what other member objects would be needed to store information about transactions (i.e. sender, recipient, transaction value, etc). Discuss what primitive type each member should have and potential implications. The final list of private members should be:
- private Integer id;
- private String sender;
- private String recipient;
- private Double transaction_value;
- private Boolean soft_delete;

soft_delete is likely a private member that the students will **not** think of on their own. Briefly discuss the definition of soft_delete that we will be using and the benefits of using such a schema.
> **soft_delete = true**: the transaction is considered to be soft-deleted and not available for calculations nor visible to the user
> **soft_delete = false**: the transaction is considered to be available for calculations and should be available to the user

Inform the students that we will be transitioning to the first activity and that they will be responsible for building out the rest of the model class.

### 06. Student Do: Modelling the Model
Explain to the students that they will essentially repeat the steps that were just demonstrated, but take things one step further by finishing the model class. The activity will guide the students through the last core piece of the model definition which is creating the getter and setter methods. 

This activity can be completed in groups / breakout rooms, but each student should have their own local instance of the Ledger Project. 

Send the students the following activity file:
- [Activity File: Modelling the Model](activities/06-Modelling-The-Model/unsolved/README.md)

### 07. Instructor Review: Modelling the Model (0:15)
Run a comprehension check poll before reviewing the activity.

Inform the students that we will skip over the review of the project import and creating the private members since we covered that previously, but encourage the students to reach out to a TA over Slack if they encountered any unresolved issues with these early steps.

Walk the students through creating the getter and setter methods for each private member. After the methods have been added, tell the students that not all of the methods may be necessary for the REST APIs that we will be writing today, but that they could be used for future development should the students choose to go back to the Ledger Project at a later time and add any enhancements. 

Finally, tell the students that although the core of our model class is complete - we will be adding some annotations to be able to use this model for persistent storage.
- Above the class signature, add "@Entity". Explain that this annotation is needed to transform the class structure into a SQL table
- Above the "id" private member, add "@Id" and "@GeneratedValue(strategy=GenerationType.AUTO)". These annotations are needed to set the "id" member as the primary key of our table, and to auto-increment the "id" of each transaction as they are added.
	 > **Note:**  Ask the students if they recall what a primary key is and ask for a volunteer for the definition as a friendly reminder for the class
- Lastly, add the following imports to the class:
	- import javax.persistence.Entity;
	- import javax.persistence.GeneratedValue;
	- import javax.persistence.GenerationType;
	- import javax.persistence.Id;

Tell the students to go ahead and make these additions to their models as well, since they are rather minor additions and should just take a couple of minutes to add. Congratulate the students on successfully finishing their model!

### 08. Instructor Do: Interacting with the Interface (0:07)
Open the TransactionRepository.java class (provided in the starter code). Explain to the students that this is an interface which extends CrudRepository. We will use this to access, store, and modify our transactions. Also explain that this will be **auto implemented** by Spring into a Bean called transactionRepository

Now that we have implemented our Transaction class, we can uncomment the commented out portion of the class signature. Explain that this was initially commented out because it would have thrown an error without the Transaction class defined. 

Ask students to also uncomment this code (it should be a relatively trivial task), and if they have any further questions about this step or its significance before proceeding.

### 09. Instructor Do: Commanding the Controller & Building our first CRUD API (0:15)

Open the pre-defined class-stub for MainController.java. Start by explaining to the students that this will be the main controller of our application (hence the name)! 

Start by walking through the class-stub and explaining the significance of the following annotations:
- **@Controller** - This defines our class as a controller for the Spring MVC framework
- **@RequestMapping(path="/api")** - This is an annotation which directs Spring to invoke each of our APIs under the path "/api". For example, if we later define an API with a path of "/test", the final request should be made to ${HOSTNAME}/api/test. Explain that we define this to create a special path for all APIs, and to not conflict with any other file paths that may exist on our server. "path" is an input parameter passed into "RequestMapping", and the String value can be modified to be something other than "/api".
- **@Autowired** - This annotation is used to get the bean "transactionRepository" (which we just walked through in the previous section of our lesson plan) 
- **@PostMapping**, **@GetMapping**, **@PutMapping**, and **@DeleteMapping**: These should be somewhat intuitive, they correspond to the type of HTTP request bound to the path passed in as an input parameter. Examine the annotation of first method, addNewTransaction ( @PostMapping(path="/addTransaction") ). Ask students if they can infer the type of request and final request URL that will be created from this annotation.
- **@ResponseBody**: This is an annotation that tells a controller that the object returned is automatically serialized into JSON and passed back into the _HttpResponse_ object
- **@RequestParam**: Method parameters annotated with _@RequestParam_ are required by default. This means that if the parameter isn’t present in the request, we'll get an error

Although there are some other annotations which could also be used to write our APIs, these are sufficient for developing robust APIs with the core Java foundational concepts we have learned about so far!

Creating our first CRUD API:

We will begin by defining a POST endpoint that creates a single transaction in our ledger. Remind the students that this will effectively serve as our 'Create' operation (The 'C' in CRUD).

The code in the method goes as follows:
- Transaction t = new Transaction(); - Instantiates a new 'Transaction' object to map our request parameters into
- The following lines of code are all similar in nature, we will be calling the setter methods within our new object to map the values sent to us in our request 
	- t.setSender(sender);
	- t.setRecipient(recipient);  
	- t.setTransaction_value(transaction_value);  
	- t.setSoft_delete(false);
		- Make note that we always want to set the "soft_delete" member to false, since a new transaction cannot be a "deleted" one. We also do not want the caller of the API to handle setting this value, which is why it is not a request parameter

- transactionRepository.save(t); - This method will accept our Transaction object as an input parameter and save it to our connected database
- return "Transaction Successfully Saved"; - This is a string to let the user know that their request was successfully recorded

Finally, run the application and make a request to our new route using either Postman or cURL. Explain that if we receive the success message as a response, that our API is up-and-running!  

### 10. Student Do: CREATEing our first CRUD API (0:10)
Explain to the students that they will once again be mirroring the demonstration that they just witnessed. The significance of this activity is to reinforce the topics just discussed as well as confirm that the students' development machines have all dependencies up-and-running for the remainder of the activities.

This activity can be completed in groups / breakout rooms, but each student should have their own local instance of the Ledger Project. 

Send the students the following activity file:
- [Activity File: CREATEing our first CRUD API](activities/10-CREATEing-our-first-CRUD-API/unsolved/README.md)

### 11. Break (0:15)
If a student is facing any difficulties with their development machine, now would be a good time to help troubleshoot with the instructional staff

### 12. Instructor Review: Check-in with the Class (0:03)
Since a demonstration of this activity was already completed, there is no need to walk through the activity again. However, check-in with the students to gauge where the class is with building REST APIs. Direct students to a Slack channel and/or breakout room with a TA if they faced a roadblock in the previous activity.  

### 13. Instructor Do: READing the Data (0:15)
Introduce this topic by saying that now we will begin creating a few more API endpoints to handle READ operations on our database (The 'R' in CRUD). These APIs will come in the form of HTTP GET Requests. 

The first READ operation that we will create is a simple one, but one that is commonly supported by most-any application. The invoker of the API will supply an id as an input parameter, and we will search the database for the entry matching that id. If there's a match, send it in the response to the invoker.

In our "getById" method stub, type out the following line to complete the function:
- return transactionRepository.findById(id); - This will invoke the "findById" method in our TransactionRepository to search for the data entry with the provided id (if it exists). 
	> **Note:** findById is an inherited method from CrudRepository which we extended. Encourage students to explore the spring docs to learn more about this interface

Restart the Spring Application and make a GET request to the supplied route using the id of a previously created entry (again, Postman or cURL)

Return to the IDE and type out the "getAllUsers()" method. Explain to the class that we will be writing an API to retrieve all available transactions. An available transaction is one that has not been "soft deleted". Remind students quickly what a soft delete is and why this API would only want the transactions that have not been soft deleted.

- Iterable\<Transaction\> allTransactions = transactionRepository.findAll(); - This will invoke the findAll() method from our transaction repository and store them in a newly created collection of "Transactions". Remind students of the Iterable collection if necessary.
- ArrayList\<Transaction\> availableTransactions = new ArrayList<>(); - An empty collection that we will use to store transactions that are "available".
- for (Transaction t: allTransactions)  
  {  
        if(t.getSoft_delete() == false)  
        {  
	           availableTransactions.add(t);  
        }  
  }
	-	Speak to the students that our strategy for finding the available transactions is to step over each individual transaction using a foreach loop, and add the transactions where "soft_delete == false". Point out that we are using our getter method defined earlier to retrieve the value of the soft_delete boolean for each transaction. Finally, add the transactions where "soft_delete == false" to our empty ArrayList.

- return availableTransactions; - Our final return statement which will hand-off our list of available transactions to be serialized as a response object

Restart the application again and perform a series of POST & GET requests to populate the database with transactions, and finding transactions by id or by list of available transactions. 
 
### 14. Student Do: Get, Get, GET! (0:15)
Introduce the next activity by telling the students that they will be adding the 2 API endpoints that we just discussed, as well as adding a 3rd API endpoint to get the sum of all available transactions. They should use the 2nd GET Request we just discussed (GET all available transactions) as a starting point for this new endpoint. 

This activity can be completed in groups / breakout rooms, but each student should have their own local instance of the Ledger Project. 

Send the students the following activity file:
- [Activity File: Get, Get, GET](activities/14-get-get-get/unsolved/README.md)

### 15. Instructor Review: Get, Get, GET! (0:05)
The review for this activity should be fairly brief as students have already seen the instructor demo for the first 2 API endpoints, and the 3rd new endpoint is fairly similar in nature to its predecessor. Use this time to field any additional questions students have up to this point.

### 16. Instructor Do: UPDATE & DELETE (0:05)
At this point, students should be able to see the direction we are headed in with the UPDATE and DELETE CRUD operations (The 'U' and 'D' in CRUD). The remainder of class will consist of a brief overview of how UPDATE and DELETE work, and then allowing the students to try and implement the final API endpoints on their own in the final activity. 

- **Update:**  Changes the data of one or more records in a table. Either all of the rows can be updated, or a subset may be chosen using a condition. An update is typically invoked by a PUT HTTP request.
- **Delete:** removes one or more records from a table. A subset may be defined for deletion using a condition, otherwise all records are removed. A delete is invoked by a DELETE HTTP request.

### 17. Student Do: One PUT and a DELETE (0:15)
Introduce the final activity by telling students that we will be using everything we have learned up to this point to write 2 final API endpoints: 
- A PUT endpoint to update the "transaction_value" of a transaction, the transaction is specified by a provided id input parameter.
- A DELETE endpoint to "soft delete" a transaction, this is done by updating the "soft_delete" flag to true for the transaction

This activity can be completed in groups / breakout rooms, but each student should have their own local instance of the Ledger Project. 

Send the students the following activity file:
- [Activity File: One PUT and a DELETE](activities/17-one-put-and-a-delete/unsolved/README.md)

### 18. Instructor Review: One PUT and a DELETE (0:05)
Walk students through the solution for the PUT and DELETE API endpoints. 
- PUT: Retrieve the transaction by id (similar to how this was done in our first GET request), if the transaction exists, use the setter to update its value & save. 
	- Optional\<Transaction\> t = transactionRepository.findById(id);  
if(t.get() != null)  
{  
    t.get().setTransaction_value(transaction_value);  
  transactionRepository.save(t.get());  
}  
return t;

- DELETE: Similar to the PUT request in nature, except we are setting "soft_delete" to true rather than updating the transaction_value, and returning a String to inform the invoker that the transaction was successfully deleted. 

Congratulate the students on completing the Ledger Project (or at least learning the fundamentals of CRUD and how it works with REST). Answer any remaining questions that students may have.


```
