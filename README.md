# Microservice Testing

# accounts
accounts is a sample microservice application by spring boot
accounts includes sample tests for different layers of an instance microservice.
-  Sociable Unit testing for Entities(They are not anemic)
-  Solitary Unit testing for Services
-  Integration test for Repositories
-  Component test for overall rest service test.
-  CDC test for API testing on the provider side.

# cheques
cheques is a java sample client application for accounts service and includes a sample consumer test (CDC by Pact).
-  CDC test for integration testing(transfer service), consumer side.
-  workflow test(not finished) 
# bank-ui
bank-ui is a simple angular application that consumes accounts service and includes unit and consumer tests (CDC by Pact).
-  Unit testing for Angular components(Jest).
-  CDC test for integration testing with backend(consumer side) by Pact. 
-  E2E testing by Cypress(not finished) 

# pacts
pacts is a folder for sharing contracts between consumers(bank-ui,cheques) and provider(accounts) in CDC tests.

# Test Tools
-  JUnit(jupiter)
-  Spring Framework Testing
-  Mokito
-  Hamcrest
-  RestAssured
-  Pact
-  Jest
-  Angular Testing
-  

## How to run tests for accounts
 ```
mvn clean package 
```
This command runs all tests including CDC tests.
Or by running as JUnit test in IDE


## How to run tests for bank-ui
```
npx jest -i
```
This command runs all tests including CDC tests.-i option execute pact test serially.
```
npx jest -t account
npx jest -i -t pact
npx jest -i .pact.spec.ts
npx jest .component.spec.ts

```
 npx jest by a filter 

## How to run accounts microservice standalone
Inside accounts folder run 
```
mvnw spring-boot:run

```
It starts accounts microservice.
accounts microservice uses an in-memory database.
It seeds database by sample data on startup.
If accessing an external port has limited in your organization, this command (mvnw spring-boot:run) won't work. In the accounts folder run something like flowing command instead.
```
java -jar target/product1-0.0.1-SNAPSHOT.jar

```
## How to run bank-ui standalone

Inside bank-ui folder in command run
```
ng serve --proxy-config proxy.conf.json

```
It starts bank-ui Angular web application.
For watching communication logs you can use --verbose option,
like this.
```
ng serve --proxy-config proxy.conf.json --verbose

```

## How to run the whole application by docker compose (accounts,bank-ui) 

Inside microservice-test(root) folder in command run
```
docker-compose -f "docker-compose.yml" up -d --build

```
It creates related docker images and starts both bank-ui Angular web application and backend accounts microservice containers.
This way bank-ui can call backend microservice by its container name and related Url.


## Documentation
Learn more about how to test 

- A great article by Ham Vocke
https://martinfowler.com/articles/practical-test-pyramid.html

- Chapter 9 and 10 about testing from Microservices patterns book 
https://microservices.io/book

- CDC test
https://docs.pact.io/
https://reflectoring.io/consumer-driven-contracts-with-angular-and-pact/

- jest 
https://www.xfive.co/blog/testing-angular-faster-jest/

