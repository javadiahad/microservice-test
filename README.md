# microservice-test

product1 includes sample tests for different layers of an instance spring boot microservice.
1-Sociable Unit testing for Entities(They are not anemic)
2-Solitary Unit testing for Services
3-Integration test for Repositories
4-Component test for overall service test.
5-CDC test for API testing 



product2 is a java client for Product1 microservice and includes a sample consumer test (CDC by Pact).
1-CDC test for integration testing with calling service


product1-ui is a simple angular application that consumes Product1 service and includes unit and consumer tests (CDC by Pact).

1-Solitary Unit testing for Angular components
5-CDC test for integrationtion testing with backend 

pacts is a folder for sharing contract between consumers(product1-ui,produc2) and provider(product1) in CDC tests.

How to run tests for product1
1-mvn clean package 
This command runs all tests including CDC tests.
2-By running as JUnit test in IDE


How to run tests for product1-ui
1-npx jest in command 
This command runs all tests including CDC tests.
2-npx jest by filter
npx jest -t account


How to run Product1
Inside Product1 folder run (mvnw spring-boot:run). It starts product1 microservice
Product1 microservice uses an in-memory database and it seeds database by sample data on startup
if accessing to external port has limited in your organization so this command (mvnw spring-boot:run) doesn't work. So in the Product1 folder use something like this(java -jar target/product1-0.0.1-SNAPSHOT.jar) instead

How to run product1-ui

Inside Product1-ui folder in command run (
ng serve --proxy-config proxy.conf.json) it starts product1-ui Angular web application.
For watching communication logs you can use --verbose like this
ng serve --proxy-config proxy.conf.json --verbose


