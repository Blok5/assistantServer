docker-compose -f src/main/docker/docker-compose.yml down
docker rmi sb-assistant-app:latest
docker rmi postgres:13.1-alpine
./mvnw clean package
cd sb-assistant-app/
cp target/*.jar src/main/docker
docker-compose -f src/main/docker/docker-compose.yml up