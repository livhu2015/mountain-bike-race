#### Swagger UI
-  `http://127.0.0.1:8080/swagger-ui/index.html#/`

### TheBusiness Requirement Specification
We require the application to display the following reports:
1. The fastest 3 riders per race.
2. Riders that did not finish.
3. All riders that did not take part in the race.
4. The weather conditions/forecast for the race location (time can be current time or
   starting time of the race. It doesn’t matter, but please use a public API).ong id): This is a DELETE endpoint that deletes the Race object with the given id from the system. The @DeleteMapping annotation maps this method to the URL path /delete/{id} where {id} is a path variable.