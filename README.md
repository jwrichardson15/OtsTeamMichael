# National Parks Backend

Java Spring backend for National Parks ticket service

## Running the database

The database is expected to be running on `localhost:5432` for development and testing. This can be achieved the following ways:

### Running the Database with Docker

A preconfigured Postgres instance is also provided by `docker-compose.yml`. After installing Docker for Mac <https://docs.docker.com/docker-for-mac/install/>, the database can be run locally with `docker-compose up`. If you'd like to background the process, additionally add the `-d` flag.

If you begin noticing database instability, this might be fixed by restarting the Docker daemon via the Docker for Mac toolbar icon

### Running the database locally

Currrently, the database is expected to be running on `localhost:5432` with the database `parks`, username `parks`, and password `parksdev`.

## Running the application

With the database running, the application can be run with Maven with

    mvn spring-boot:run

## Running Tests

Tests can be run with

    SPRING_PROFILES_ACTIVE=standalone mvn test

This will produce test coverage results in `target/site/jacoco/`.

## Packaging the JAR

The production JAR can be packaged with:

    SPRING_PROFILES_ACTIVE=standalone mvn package

This will produce a `parks-*.jar` file in the `targets/` directory.

## Continuous Integration

Continuous Integration is provided by Amazon CodeBuild, with the test configuration available in `buildspec_test.yml`. On each pull request and push, the code will be pulled and tested, with the test status reported back to Github.

## Continuous Deployment

Continuous Deployment is provided by Amazon CodePipeline, with the build configuration available in `buildspec_build.yml`. On each commit to `master`, the code will be pulled, tested, built, and deployed to Elastic Beanstalk.
