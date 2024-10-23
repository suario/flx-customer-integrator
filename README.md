# Assessment Task

# New Features

New additions were added after the deadline, if the reviewer wants to test them, he/she can do so by checking out the **v2** branch from the repository, otherwise, keep reading from the **Requirements** section :

https://github.com/suario/flx-customer-integrator/tree/v2

Change Log:
* Complete business logic
* Improved mappers
* Added a separate **id** to differentiate the local auto-increment id from the tracked customer ID.
* Added mechanism to force failure based on a percentage.

**failureRate** is a new property added to **flx-crm-service** with the intention of mimicking the intermittent failure behavior that can happen in a real system. 

A value of 0 means 0% of failed responses, a value of 100 means 100% of failed responses from the CRM. By default the value is 30.

This was done to test the retry mechanism without stopping the CRM.

![image](https://github.com/user-attachments/assets/f4cf9572-c285-4cea-9910-1f3d13db9efd)






## Requirements
* Java 17

## Architecture

The folder structure and dependency management follows the Clean Architecture principles.

The domain layer which contains the business logic of the application is located in the core package.

Repositories and webclients are connected to the business logic by using the Adapter pattern and Dependency Inversion principle.

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)


There are two services:
* flx-integrator: Integrator
* flx-crm-service: Service that emulates the CRM

For the integrator the database structure goes like this:

![image](https://github.com/user-attachments/assets/0aa1e0df-76fc-43f7-a4c5-da15c05d4eef)

And for the CRM, the database contains a single table that look like this:

![image](https://github.com/user-attachments/assets/4f5e0d9a-8651-4928-a68d-555d105094d3)

Each service uses a different in-memory database.

## Assumptions
* All the fields are required
* Fields firstName, lastName and city can contain letters and spaces
* street field can contain alphanumeric characters and spaces
* zipCode admits only numbers
* state is a two character word with only valid USA states (ex. IL, FL)

## Solution

flx-integrator is encharge of calling the CRM, the web client is always called before saving the data, so since data consistency is required, whenever the call to the CRM fails for whatever reason, an exception is thrown and the flow of actions is broken, meaning the data won't be stored locally either.

![image](https://github.com/user-attachments/assets/e8b6dcd3-97f8-4394-b46f-52d56358d06a)

An error response is returned by the system.

![image](https://github.com/user-attachments/assets/93ff62a0-14ad-465b-ac69-291a24af5b53)


### Retry Strategy

The retry strategy was implemented by using **resilience4j** with the following configuration:

![image](https://github.com/user-attachments/assets/6250672c-0119-4e5a-948e-a8dd5f0dccc4)

As the reader can see, the exception with name ResourceNotFoundException is excluded from the retry configuration, this exception is used to map the 404 HTTP status in the reactive chain of the client (see picture below). This is because a 404 is a valid status that is exposed by the CRM when a resource is not found. In summary, when a 404 status is obtained from the CRM, no retry strategy will be triggered.

For the REST client, each method has been marked with the @Retry annotation with a fallback method to throw a proper exception.

![image](https://github.com/user-attachments/assets/3e17d4ba-869e-46ef-81a3-2f6507e81b3c)


After stopping the CRM service to emulate a faulty service and calling the CREATE, UPDATE or DELETE actions, the result of the retry strategy will look like this in the logs:

![image](https://github.com/user-attachments/assets/30ddb7cd-b2ae-4641-b8c6-dffdf6b44db3)

### Error Handling

When a validation issue happens a business exception is thrown

![image](https://github.com/user-attachments/assets/10da5d4b-a121-40ac-9be1-f6d3d9618a67)

The exception is then caught by an exception handler has shown below

![image](https://github.com/user-attachments/assets/00c1aa86-0294-4f46-a3b7-326f17b90a81)

All fields are validated
![image](https://github.com/user-attachments/assets/5fdf91e0-c450-4faa-bb4c-1afc6b78b1f2)


## Run

* Clone the repository
* Build both applications using gradle
* To run flx-integrator, execute FlxIntegratorApplication.main, by default it runs on port 8080
* To run flx-crm-service, execute FlxCrmServiceApplication.main, by default it runs on port 8081, if the reader need to change it, they will need to modify the value of the property **adapter.restconsumer.url** in the flx-integrator
* Open http://localhost:8080/swagger-ui.html

![image](https://github.com/user-attachments/assets/d9c2397d-4095-4756-8956-006c1e76ee80)




## Bonus

* Swagger Documentation added
* Tests added




