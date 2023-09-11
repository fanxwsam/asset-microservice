# microservice-configuration

The whole microservice environments for local dev, docker containers and kubernetes.

There are 6 microservices which are,
- **api-security** for Authentication and Authorization
- **api-user** for user, account, user details management
- **api-customers** for registration, which simulates the interaction between microservices using API call 
- **api-fraud** for fraud checking before customers' registration
- **api-notifications** for sending notifications, which simulates using RabbitMQ  
- **api-message** for sending messages, which simulates using Kafka 


The 6 microservices run with the support of Postgres database, CosmosDB NOSQL, API Gateway, OAuth2, RabbitMq, Kafka and Zipkin which are also containerized and managed through Kubernetes.
So, by default, there are 14 services created in Kubernetes.

- api-security is the Authorization Server and all endpoints are secured with this server
- Customer register API calls Fraud check API
- Customer Register and Fraud Check talk to each other through container name in Docker or through the service name defined by Kubernetes
- And then publish message to Rabbit MQ
- In the meantime, publish the message to Kafka
- The listener in notification reads the message from the Rabbit MQ and save data in database
- And the message consumer in Microservice Messages reads data from Kafka and save the data as well.
- Each microservice has its own database.
- All the microservice images are published to the Dockerhub.
- Slueth was introduced for the distributed service tracing
- Zipkin for the trace report display
- api-user is just a sample for integration when we have a different requirement (it uses Azure CosmosDB)
- The whole enviornment can be deployed in Minikube locally and all the configurations have been done
- Without few changes, the whole environment can be deployed on AWS/AZURE/...

The architecture,
![Architecture](https://user-images.githubusercontent.com/46641840/185408277-8a1e728a-8210-4f76-b5b1-e648c6205520.png)

Solution stack:
- Java
- Springboot
- OAuth2 (Spring Security)
- Hibernate
- Postgres Database
- Maven
- RabbitMQ
- Kafka
- Eureka
- Zipkin
- API Gateway
- Docker
- Minikube
- Kubernetes
- Azure
- Azure CosmosDB

### Run microservices locally (Postgres, Rabbitmq, Zipkin run in Docker for bootstrap):

- Download the whole project and open the project using IntelliJ
- This project uses some features of JDK Version 17+, please ensure JDK17 has been installed and configured
- Open file "docker-compose.yml", comment the services kafka, zookeeper, api-customers, api-notifications, api-messages, api-fraud and api-users
- add a local host in your machine, on windows change file 'hosts' in folder 'C:\Windows\System32\drivers\etc', add '127.0.0.1      api-security' 
- Open file "pom.xml" in the root folder, and add your own credential like below

  ![image.png](assets/credential.png)
- In Terminal, login with your Dockerhub
- Enter into the main project directory
- Run command: mvn clean package -P build-docker-image
- If there are some new versions, run command below to get the latest version. If it's first run, the next step will pull all the related images automatically
  ```docker compose pull```
- Run command: docker compose up -d
- Run command: docker exec -it postgres psql -U postgres
- Run command to create 4 database
  create database customer;
  create database notifications;
  create database messages;
  create database fraud;
- Prepare for Kafka enviornment
  - download kafaka: https://www.apache.org/dyn/closer.cgi?path=/kafka/3.3.1/kafka_2.13-3.3.1.tgz
  - enter into the main folder 'kafka_2.13-3.3.1' and start kafka
    ```
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
    .\bin\windows\kafka-server-start.bat .\config\server.properties
    ```
- Start the 6 services in IntelliJ

  Before starting the 6 services, set the active profile to 'default' in IntelliJ

  ![image.png](assets/profile_setting.png)
- Test using Postman:
  Configurations of Authorization in Postman
  ![image.png](assets/postman_config_auth.png)
  http://localhost:8080/api/v1/customers
  ```
  POST
  {
      "firstName":"Tom",
      "lastName":"Jerry",
      "email":"tom_jerry@gmail.com"
  }
  ```

### Run microservices in local Docker containers:

- Open file "docker-compose.yml"
- In Terminal, login with your Dockerhub
- Enter into the main project directory
- Run command: mvn clean package -P build-docker-image
- Run command: docker-compose pull
- Run command: docker compose up -d --build
- Run command: docker exec -it postgres -U postgres
- Run command to create 4 database
  create database customer;
  create database notifications;
  create database messages;
  create database fraud;
- Run command: docker compose down
- Run command to restart all the containers: docker compose up -d
- Test using Postman:
```
  http://localhost:8080/api/v1/customers
  POST
  {
      "firstName":"Tom",
      "lastName":"Jerry",
      "email":"tom_jerry@gmail.com"
  }
```


Run the whole environment on local Kubernetes environment (Minikube):
- Open file "docker-compose.yml"
- In Terminal, login with your Dockerhub
- Enter into the main project directory
- Run command: mvn clean package -P build-docker-image
- Run command: minikube start --memory 4g
- Enter into the folder: /k8s/minikube
- Run command: kubectl apply -f /bootstrap/postgres
- Run command: kubectl get pods
- Run command: kubectl exec -it postgres-0 bash
- Run command: psql -U postgres
- Run command to create 4 database
  create database customer;
  create database notifications
  create database messages;
  create database fraud;
- Run command: kubectl apply -f /bootstrap/zipkin
- Run command: kubectl apply -f /bootstrap/rabbitmq
- Run command: kubectl apply -f /bootstrap/kafka
- Run command: kubectl apply -f /services/customer
- Run command: kubectl apply -f /services/fraud
- Run command: kubectl apply -f /services/notifications
- Run command: kubectl apply -f /services/messages
- Run command: minikube tunnel
- Test using Postman:
  http://localhost:8888/api/v1/customers
```
  POST
  {
      "firstName":"Tom",
      "lastName":"Jerry",
      "email":"tom_jerry@gmail.com"
  }
```  

### Run the whole environment on Azure Kubernetes(AKS) environment:
- Cloud Infrastrure Management - refer to another document ( Automated by Terraform or Set Up Manually)
- Download Kubeconfig.yml file
- Set local environment variable (use 'export' on Unix, use Environment Setting on Windows )
- Enter into folder /k8s/cloud-name
- Run command: kubectl apply -f /bootstrap/postgres
- Run command: kubectl get pods
- Run command: kubectl exec -it postgres-0 bash
- Run command: psql -U postgres
- Run command to create 4 database
  create database customer;
  create database notifications
  create database messages;
  create database fraud;
- Run command: kubectl apply -f /bootstrap/zipkin
- Run command: kubectl apply -f /bootstrap/rabbitmq
- Run command: kubectl apply -f /bootstrap/kafka
- Run command: kubectl apply -f /services/eurika
- Run command: kubectl apply -f /services/apigw
- Run command: kubectl apply -f /services/api-customers
- Run command: kubectl apply -f /services/api-fraud
- Run command: kubectl apply -f /services/api-notifications
- Run command: kubectl apply -f /services/api-messages
- Run command: kubectl apply -f /services/api-users
- Run command: kubectl apply -f /services/api-security
- Test using Postman:
  http://XXXXXXXXX/api/v1/customers
  post
  {
  "firstName":"Tom",
  "lastName":"Jerry",
  "email":"tom_jerry@gmail.com"
  }

Kubernetes configurations:
![image](https://user-images.githubusercontent.com/46641840/185409770-cc6c6d04-c463-406d-82a3-664e4db6eaec.png)
