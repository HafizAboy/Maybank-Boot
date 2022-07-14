# Maybank Boot Rest API
Spring Boot application for simple credit card application

## Dependencies
This project relies mainly on Spring Boot. Mainly:
  - Spring JPA
  - Swagger
  - Maven
  - MySQL
  
Full list of dependencies can be found in [pom.xml][1].

## Requirements:
  - credit card application
  - get credit balance - getCreditCardByCreditCardNo

### Scenario
The are two roles in the system; `CUSTOMERS` and `CREDITCARDS`

#### For Customer
  - Can search, add, update, and delete Customer from the system
  
#### For CreditCard
  - Can search, add, update, and delete CreditCard from the system
  - Can apply credit card using customerId and creditCardNo
  - Can get credit card details and balance in getCreditCardByCreditCardNo

#### API testing using Swagger can be find in document below
MaybankBoot-Hafiz.docx

#### docker-compose.yml and Dockerfile provided

#### Docker Command:
- Make changes to application.yml, application.properties and Dockerfile accordingly
- Create network: docker network create springboot-maybank-docker
- Create db container: docker container run --name maybankdb --network springboot-maybank-docker -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=maybank_db -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:8
- Build springboot app image: docker build . -t maybank-service-app-api
- Run as container with network, app and db: docker container run --network springboot-maybank-docker --name maybank-service-app-container -p 8080:8080 --link maybankdb:mysql -d maybank-service-app-api

- list of container: docker container ls
- list of network: docker network ls
- list of images: docker images
- view log: docker logs *container*
- accessing db: docker exec -it maybankdb bash
- start container: docker container start *container*
- stop container: docker container stop *container*
- check docker-compose format: docker-compose config
- run docker-compose: docker-compose up

#### Locally
- To run locally, change the database config in application.yml from maybankdb to localhost, update db credential or port accordingly.
 