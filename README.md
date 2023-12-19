# Blog Post API

[![Java CI with Maven](https://github.com/ralf-ueberfuhr-ars/spring-boot-2023-12-18/actions/workflows/ci.yml/badge.svg)](https://github.com/ralf-ueberfuhr-ars/spring-boot-2023-12-18/actions/workflows/ci.yml)

This is a microservice with Spring Boot that implements a BlogPost REST API

##  Run the project

In your IDE, just run the 
[`de.sample.schulung.spring.blog.Application`](src/main/java/de/sample/schulung/spring/blog/Application.java)
class.

Alternatively, run the following command:

```bash
mvn spring-boot:run
```

Then, you can open the browser and find the available resources on
`http://localhost:9080/index.html`.

## Build the project

You can build the JAR file with Maven:

```bash
mvn clean package
```

and run it with a Java 17 runtime environment:

```bash
java -jar target/blog-app-0.0.1-SNAPSHOT.jar
```
