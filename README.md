# Assessment Task Clean Architecture

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

## Requirements
* Java 17

## Solution

For the solution of the problem, I have created two services:
* flx-integrator: Integrator
* flx-crm-service: Service that emulates the CRM

## Run

* Clone the repository
* Build both applications using gradle
* To run flx-integrator, execute FlxIntegratorApplication.main, by default it runs on port 8080
* To run flx-crm-service, execute FlxCrmServiceApplication.main, by default it runs on port 8081, if the reader need to change it, they will need to modify the value of the property **adapter.restconsumer.url** in the flx-integrator



## Bonus

* Swagger Documentation Added
* Tests added



