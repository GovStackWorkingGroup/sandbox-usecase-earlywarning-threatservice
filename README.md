# Sandbox use case - Early Warning
# Threat Service
Microservice which is responsible for handling various threats and their broadcasts. 
Threats are received from the Information Mediator, and stored in this service.
User is able to create new early warning broadcast to the end-users based on the selected threat.
Once the broadcast is created, it's published back to Information Mediator for further processing in communication service.
For testing the Early Warning system, check the user service for credentials:

# Tech stack
Microservice is using the following: <br>
Java 17, Spring Boot, H2, Kafka pub/sub, Docker/Helm charts for deployment.

# Note
In order to test the Early Warning system, make sure to run the rest of the services and frontend.