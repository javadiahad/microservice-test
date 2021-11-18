# Microservice Testing

#accounts
accounts includes sample tests for different layers of an instance spring boot microservice.
1. Sociable Unit testing for Entities(They are not anemic)
2. Solitary Unit testing for Services
3. Integration test for Repositories
4. Component test for overall service test.
5. CDC test for API testing.
# cheques
cheques is a java client for accounts microservice and includes a sample consumer test (CDC by Pact).
1. CDC test for integration testing with calling service.
# bank-ui
bank-ui is a simple angular application that consumes accounts service and includes unit and consumer tests (CDC by Pact).
1. Solitary Unit testing for Angular components.
2. CDC test for integration testing with backend. 

# pacts
pacts is a folder for sharing contracts between consumers(bank-ui,cheques) and provider(accounts) in CDC tests.

## How to run tests for accounts
1. mvn clean package 
This command runs all tests including CDC tests.
2. By running as JUnit test in IDE


## How to run tests for bank-ui
1. npx jest in command 
This command runs all tests including CDC tests.
2. npx jest by filter
for example run >npx jest -t account


## How to run accounts
Inside accounts folder run (mvnw spring-boot:run). It starts accounts microservice
accounts microservice uses an in-memory database. It seeds database by sample data on startup.
If accessing an external port has limited in your organization, this command (mvnw spring-boot:run) won't work. In the accounts folder run something like this(java -jar target/product1-0.0.1-SNAPSHOT.jar) instead.

## How to run bank-ui

Inside bank-ui folder in command
run >ng serve --proxy-config proxy.conf.json
It starts product1-ui Angular web application.
For watching communication logs you can use --verbose option,
like this.>ng serve --proxy-config proxy.conf.json --verbose


