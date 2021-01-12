# Student Guide: The Ledger Project

## Project Setup:
The following dependencies will be need to be installed to complete the Ledger Project, many of which overlap with dependencies from prior topics:
- MySQL version 5.6 or later
- MySQL Workbench
	> Downloads for these can be found at https://dev.mysql.com/downloads/. Instruct students to follow links for "MySQL Community Server" and "MySQL Workbench", as well as downloading the appropriate installer for their specified operating system. 
- JDK 1.8 or later
- Gradle 4+ or Maven 3.2+ (Maven preferred)
- IntelliJ IDEA IDE Community Edition
- Postman and/or cURL for API testing (student preference)  

## MySQL Setup / Reference:

### Initial Query:
- create database db_example; 
create user 'springuser'@'%' identified by 'ThePassword'; 
grant all on db_example.* to 'springuser'@'%';
	-	Students can change 'ThePassword' to be any password that they choose. If changed, make the same change in the application.properties file in the Spring project

application.properties provided for reference:
- spring.jpa.hibernate.ddl-auto=update  
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example  
spring.datasource.username=springuser  
spring.datasource.password=**ThePassword**

## API Execution
- It is recommended to use Postman for testing newly developed APIs. Postman is a free tool that provides a simple yet powerful user interface for constructing API requests. 
- cURL is a command-line tool which is another popular option for API execution. Documentation on cURL can be found here: https://curl.se/docs/
- If you would like to play around with both tools, Postman has a feature for converting an API request into "cURL syntax". Simply select the "Code" option on the far right-hand side of the API request you would like to generate a code snippet for.

## Additional Documentation & Resources:
- Spring Data JPA: https://spring.io/projects/spring-data-jpa
- Spring Initializr: https://start.spring.io/
- MySQL: https://dev.mysql.com/ 
- CrudRepository Interface: https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html 
- Java Optional Collection: https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
- Java Iterable Collection: https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html
- RESTful JAX-RS Annotations: https://www.javatpoint.com/jax-rs-annotations-example
