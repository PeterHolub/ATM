# ATM system implementation
Implementation of ATM (Automated Teller Machine) system using Java SE, JSP, JSTL tag library, H2 database and Jetty.
## Prerequisites
* **JDK >= 1.8.0_131**;
* **Apache Maven >= 3.5.2**
## Project structure 
  **Java classes:**
* src\main\java\atm.dao -interface that provides  design and API to implement to work with Database
* src\main\java\atm.daoimpl -concrete classes that is an implementation of the interfaces from dao package
* src\main\java\atm.model - POJO classes pack
* src\main\java\atm\filter\SessionInvalidate - Web filter for session invalidation
* src\main\java\atm.servlets.Balance - Servlet for implementation of Balance option
* src\main\java\atm.servlets.Home - Servlet for card number validation
* src\main\java\atm.servlets.PinEntry - Servlet for pin code validation
* src\main\java\atm.servlets.Withdrawals - Servlet for implementation of Withdrawals option
* src\main\java\atm.util.DatabaseConnection - Initializer of Database connection
* src\main\java\atm.util.Date - Class for generation of date and time 


**Properties files:**
* src\main\resources\databaseconnection.properties
**Web files:**
* src\main\webapp - contains jsp ,java script files & css file with deploying descriptor web.xml
**Database files:**
* db\main\atm.mv.db - H2 database file with needed data
 
## Run the App

Run script for server local deploy:
```
mvn jetty:run
```
Then, open http://localhost:8080/ in your browser

## Test Data:
```
* card number:1111222233334444
* pin code:1111
* card status:normal
* balance:5000.99

* card number:5555666677778888
* pin code:2222
* card status:blocked
* balance:1000.02


```