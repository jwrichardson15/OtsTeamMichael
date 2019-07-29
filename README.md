# National Parks Backend

Java Spring backend for National Parks ticket service

## Running the Database with Docker

A preconfigured Postgres instance is also provided by `docker-compose.yml`. After installing Docker for Mac <https://docs.docker.com/docker-for-mac/install/>, the database can be run locally with `docker-compose up`. If you'd like to background the process, additionally add the `-d` flag.

If you begin noticing database instability, this might be fixed by restarting the Docker daemon via the Docker for Mac toolbar icon

## Running the database locally

Currrently, the database is expected to be running on `localhost:5432` with the database `parks`, username `parks`, and password `parksdev`.

## Running with Maven

    mvn spring-boot:run

## Packaging the JAR

    mvn package
