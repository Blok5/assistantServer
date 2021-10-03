:: build maven app
./mvnw clean package

:: copy app.jar to docker folder
cp sb-assistant-app/target/*.jar src/main/docker

:: remove old images
docker rmi sb-assistant-app:latest
docker rmi postgres:13.1-alpine

cp -v sb-assistant-front-app/build/* sb-assistant-app/src/main/resources/static

:: build new images
docker-compose -f src/main/docker/docker-compose.yml build

rem docker-compose -f src/main/docker/docker-compose.yml down
rem docker rmi sb-assistant-app:latest
rem docker rmi postgres:13.1-alpine
rem ./mvnw clean package
rem cp sb-assistant-app/target/*.jar src/main/docker
rem docker-compose -f src/main/docker/docker-compose.yml up