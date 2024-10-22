# Assessment Task

## Requirements
* Java 17

## Architecture

The folder structure and dependency management follows the Clean Architecture principles.

The domain layer which contains the business logic of the application is located in the core package.

Repositories and webclients are connected to the business logic by using the Adapter pattern and Dependency Inversion principle.

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Validations
* All the fields are required
* Fields firstName, lastName and city can contain letters and spaces
* street field can contain alphanumeric characters and spaces
* zipCode admits only numbers
* state is a two character word with only valid USA states (ex. IL, FL)

## Assumptions
* By default, both applications are synchronized

## Solution

For the solution of the problem, I have created two services:
* flx-integrator: Integrator
* flx-crm-service: Service that emulates the CRM

For the integrator the database structure goes like this:

![image](https://github.com/user-attachments/assets/0aa1e0df-76fc-43f7-a4c5-da15c05d4eef)

And for the CRM the database contains a single table that look like this:

![image](https://github.com/user-attachments/assets/4f5e0d9a-8651-4928-a68d-555d105094d3)



### Retry Strategy

The retry strategy was implemented by using **resilience4j** with the following configuration:

![image](https://github.com/user-attachments/assets/6250672c-0119-4e5a-948e-a8dd5f0dccc4)

As the reader can see, the exception with name ResourceNotFoundException is excluded from the retry configuration, this exception is used to map the 404 HTTP status in the reactive chain of the client (see picture below). This is because a 404 is a valid status that is exposed by the CRM when a resource is not found. In a brief, when a 404 status is obtained from the CRM, no retry strategy will be triggered.

For the REST client, each method has been marked with the @Retry annotation with a fallback method to throw a proper exception.

![image](https://github.com/user-attachments/assets/3e17d4ba-869e-46ef-81a3-2f6507e81b3c)


After stopping the CRM service to emulate a faulty service and calling the CREATE, UPDATE or DELETE actions, the result of the retry strategy will look like this in the logs:

![image](https://github.com/user-attachments/assets/30ddb7cd-b2ae-4641-b8c6-dffdf6b44db3)

### Error Handling

When a validation issue happens a business exception is thrown an broadcasted

![image](https://github.com/user-attachments/assets/10da5d4b-a121-40ac-9be1-f6d3d9618a67)

The exception is then caught by an exception handler has shown below

![image](https://github.com/user-attachments/assets/00c1aa86-0294-4f46-a3b7-326f17b90a81)



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



