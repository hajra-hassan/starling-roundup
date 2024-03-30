# Starling RoundUp 
Running the application
Validate/Refresh the Access Token:
- Go to the Starling Bank Developers Account and refresh the AccessToken
- Replace the property bearerToken in the file"application.properties"in the project
  ![Picture01](https://github.com/hajra-hassan/starling-roundup/assets/62060300/96a0d353-f65a-4af0-94e5-cff7040a4296)













How to run the application:
- Using an IDE, go to a class called StarlingtestApplication.java and run/debug.
- Or using the command"mvn spring-boot:run"on a terminal
Get call on Postman API: http://localhost:8080/starling-test/round-up







											
API calls
To make this work, the key parts from our public API you will need are:
1.	Accounts - To retrieve accounts for the customer
2.	Transaction feed - To retrieve transactions for the customer
3.	Savings Goals - Create a savings goals and transfer money to savings goals


		Accounts- https://api-sandbox.starlingbank.com/api/v2/accounts'

![Picture4](https://github.com/hajra-hassan/starling-roundup/assets/62060300/ce8f98eb-2313-4063-8c2e-01617451ad9d)







													
	Transaction Feed- https://api-sandbox.starlingbank.com/api/v2/feed/account/{accountUid}/settled-transactions-between?minTransactionTimestamp={minTransactionTimestamp}&maxTransactionTimestamp={maxTransactionTimestamp}


![Picture5](https://github.com/hajra-hassan/starling-roundup/assets/62060300/2673e747-10e2-4d85-8a5c-f47ad2e2b76d)











	Create Savings Goal- 'https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals'

 ![Picture6](https://github.com/hajra-hassan/starling-roundup/assets/62060300/64bc69fb-3d22-4f2a-80bc-a970315c7266)





 
	Get Savings Goal https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}
 
![Picture7](https://github.com/hajra-hassan/starling-roundup/assets/62060300/39fc4a24-a8a8-47d1-9632-e82f4cf90c4f)














	Result of API running

![Picture8](https://github.com/hajra-hassan/starling-roundup/assets/62060300/f46f5c29-8e59-4a05-bcf9-cb2568fe04f8)

