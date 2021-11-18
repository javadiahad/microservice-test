# Microservice Testing

# accounts
accounts include sample tests for different layers of an instance spring boot microservice.
-  Sociable Unit testing for Entities(They are not anemic)
-  Solitary Unit testing for Services
-  Integration test for Repositories
-  Component test for overall service test.
-  CDC test for API testing.
# cheques
cheques is a java client for accounts microservice and include a sample consumer test (CDC by Pact).
-  CDC test for integration testing with transfer service.
# bank-ui
bank-ui is a simple angular application that consumes accounts service and includes unit and consumer tests (CDC by Pact).
-  Solitary Unit testing for Angular components.
-  CDC test for integration testing with backend. 

# pacts
pacts is a folder for sharing contracts between consumers(bank-ui,cheques) and provider(accounts) in CDC tests.

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
```
 npx jest by a filter 

## How to run accounts
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
## How to run bank-ui

Inside bank-ui folder in command run
```
ng serve --proxy-config proxy.conf.json

```
It starts product1-ui Angular web application.
For watching communication logs you can use --verbose option,
like this.
```
ng serve --proxy-config proxy.conf.json --verbose

```

