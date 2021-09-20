# assistantServer
Server project for hackathon

1) Package maven app

$ ./mvnw clean package -DskipTests

2) go to sb-assistant-app directory

3) run our Spring Boot application and PostgreSQL with Docker Compose file

$ docker-compose up

4) local check in browser:
http://localhost:8080/api/v1/hello

# Info:

To see what network(s) your container is on, assuming your container is called "app":

$ docker inspect c1 -f "{{json .NetworkSettings.Networks }}"

How do we rebuild our Docker image with this updated application JAR file?

The best way is to remove the existing Docker image whose name we specified in the docker-compose.yml. 
This forces Docker to build the image again the next time we start our Docker Compose file:

1) cd src/main/docker 
2) docker-compose down 
3) docker rmi docker-spring-boot-postgres:latest 
4) docker-compose up
