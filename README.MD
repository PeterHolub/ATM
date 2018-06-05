# ATM system implementation
Implementation of ATM (Automated Teller Machine) using Spring Boot (Spring MVC, Spring Data JPA, Thymeleaf template and H2 Database)
## Prerequisites
* **JDK >= 1.8.0_131**;
## Project structure 
  **Functionality:**
1.1.  ATM Home Page - Page with card number input. Once the user 
inputs the card number and clicks the OK button, a request is sent 
to the database. If you find a non-blocked card, then go to the 
page with PIN input or the output page with an error message. 
1.2.  As soon as the user enters a PIN code and clicks OK, the PIN they 
entered is compared with the PIN stored in the Database. If the 
codes match, the “Operations” page loads. Otherwise, an error 
message is shown. A user can enter the wrong PIN code not more 
than 4 times. After the fourth time a message is sent telling the 
user their card is blocked, and a request is sent to block the card. 
1.3.  Depending on what operation the user selects, either the 
“balance” or “withdrawal” page is loaded.  
1.4.  If the user selects the balance summary, the operations table entry 
is added to the card with ID, time, and operation code. 
1.5.  If the user chooses "Withdrawal,” he or she can enter the amount 
of money they would like to withdrawal and click “OK.” The system 
should check and make sure the amount entered does not exceed 
the account balance. If it does, an error message should be 
displayed. Otherwise, a table entry is added with the Card ID, 
Operation Code, and amount withdrawn. The table card changes 
the amount in the account, and then loads a report on the results 
of the operation. 

  **Interface:**
2.1.  Page with Card Number Entry: There is a field that displays the 
number of the card, buttons with the integers 0-9, and "OK" and 
"Clear" buttons. The user is prompted to enter his/her 16-digit 
card number. The only possible way to enter is to click on the 
numbered buttons. In the display, the numbers are divided into 
groups of 4, for example, the number "1111111111111111" will be 
displayed as "1111-1111-1111-1111". Pressing the "Clear" button 
will reset the entered numbers. 
 2.2.  “PIN entry” page: There is a field in which the PIN code characters 
are displayed, a keyboard input, and "Clear”, “OK” and "Exit” 
buttons. The user is prompted to enter his/her four-digit PIN code. 
The procedure is similar to entering the card number, except that 
the output is displayed in the field is not user-entered digits; 
instead, these digits are displayed with characters like "*". Pressing 
the "Clear" button resets the entered numbers. 
2.3.  “Operations” page. There are 3 buttons on this page: "Balance", 
"Remove sum", "Exit". 
2.4.   “Balance” page. This page provides information about the card-
holder’s account, including today’s date, the amount in the 
account, and two buttons-"Back" and "Exit". 
2.5.  “Withdrawals” page. It contains a field for entering numbers, a 
numeric keypad, and the button s: "Delete", "OK" and "Exit". 
2.6.  “Operation Report” page. It contains information about the card 
number, date / time, the amount withdrawn, account balances, as 
well as the buttons "Back" and "Exit". 
2.7.  Error message. This contains text and a "Back" button. 







## Run the App

Run script for server local deploy:
```
mvnw spring-boot:run
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

