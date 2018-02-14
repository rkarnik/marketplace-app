# marketplace-app

This is a spring boot application. To start the application use this command.
##### mvn clean spring-boot:run

Curl commands to invoke rest endpoints 

###To create a seller
curl -X POST 
  http://localhost:8080/v1/users \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 7a9e08ae-3bd9-ba65-b19e-88feef5689c3' \
  -d '{ 
	"firstName" : "seller_firstName_1",
	"lastName" : "seller_lastName_1",
	"companyName" : "seller_company_1"
		
}'

###To create a buyer
curl -X POST \
  http://localhost:8080/v1/users \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 03f529bb-4d56-9d0f-9531-9e768a2b5c60' \
  -d '{ 
	"firstName" : "buyer_firstName_1",
	"lastName" : "buyer_lastName_1",
	"companyName" : "buyer_company_1"
		
}'

###To create a project
curl -X POST \
  http://localhost:8080/v1/projects \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 95b76125-a2d1-5a4c-6cbf-427ce2e24f48' \
  -d '{
  "name" : "project2",
  "description": "project1_description1",
  "companyName": "buyer_company_1",
  "maxBudget": "1000",
  "deadLineTime": 1518561000000,
  "sellerUuid" : "0A9839111A7249E09F77583AB9C30FDC"
}'

###To create a bid
curl -X POST \
  http://localhost:8080/v1/bids \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 05fd5b63-be89-ac6b-65d0-e5b8b7839f2f' \
  -d '{
  "bidValue": 600,
  "buyerUuid" : "F768067A8A8F43F4A27BD6C46524096C",
  "projectUuid" : "CF54B2665E8B4D8581E49F643D910CEB"
}'

###To get a project by Uuid
curl -X GET \
  http://localhost:8080/v1/projects/CF54B2665E8B4D8581E49F643D910CEB \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 68352fb6-1512-3e8b-eadb-2e45f9156f90' \
  -d '{
  "name": "project_1",
  "description": "project1_description1",
  "companyName": "buyer_company_1",
  "maxBudget": "2000",
  "deadLineTime": 1518498106,
  "seller": {
    "uuid": "AE4233A83155445C830FD61982005722",
    "firstName" : "Robert",
    "lastName" : "Stark"
    
  }
}'

###To get a project by bid status
curl -X GET \
  'http://localhost:8080/v1/projects?projectBidStatus=OPEN' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 864e30ed-cfa5-5e53-0101-7f76f6d24e75' \
  -d '{
  "name": "project_1",
  "description": "project1_description1",
  "companyName": "buyer_company_1",
  "maxBudget": "2000",
  "deadLineTime": 1518498106,
  "seller": {
    "uuid": "AE4233A83155445C830FD61982005722",
    "firstName" : "Robert",
    "lastName" : "Stark"
    
  }
}'


The time the exercise took (after dev environment is set up)
90min
Exercise Difficulty: Easy, Moderate, Difficult, Very Difficult
Easy
How did you feel about the exercise itself? (1 lowest, 10 highest—awesome way to assess coding ability)
8
How do you feel about coding an exercise as a step in the interview process?  (1 lowest, 10 highest—awesome way to assess coding ability)
9
What would you change in the exercise and/or process?
And some UI related tasks.