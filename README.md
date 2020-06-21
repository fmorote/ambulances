# Ambulances

## How to run  

### Get from github
Run `git clone https://github.com/fmorote/ambulances.git`

### Build artifact

Run `mvn clean install` to build the project. The build artifacts will be stored in the `ambulances-server/target` directory.

### Build image in docker

In `ambulances-server` folder. Run `mvn spring-boot:build-image` to build the image in docker.

## Running docker image

Run `docker run -d -p 8080:8080 -t spring-boot:1.0`.

## Browser the aps

Open a browser and go `http:\\localhost:8080`.
