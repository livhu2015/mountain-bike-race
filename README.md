## client-service-boot
 REST API that allows for creating, updating and searching for a client.

 A client should have the following fields, fields marked with * a mandatory Client
 - First Name *
 - Last Name *
 - Mobile Number
 - ID Number *
 - Physical Address

When a client is created or updated the following fields should be validate ID Number
1. Must be a valid South African ID number.
2. No Duplicates ID numbers and Mobile Number.

Note: When validation fails an appropriate response should be provided.
   You should be able to search for a client using any one of the following fields FirstName or ID  Number or Phone Number
   The data created does not have to be persisted to any database.

- Please provide a link to an open cloud Git repo. 
- Please attempt to solve the problem using TDD. 
- You are allowed to use any languages or frameworks of your choice.


## Swagger UI
http://localhost:8080/swagger-ui/index.html