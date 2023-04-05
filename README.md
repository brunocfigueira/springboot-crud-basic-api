# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#using.devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### API Documentation
Follow the link to access the swagger api documentation and api-docs:

* [Swagger API](http://localhost:8080/swagger-ui/index.html)

### Information
* Database: PostgreSQL (image Docker)
* JWT Token
* Validation Authorize
* Root User configuration (not can delete)
* Migration with flyway
  * Tables (Users, Profiles, Permissions, Profile Permission)
  * Seed early records

### Deploy in producion

* Generate .jar of the application:
  Maven -> Lifecycle -> Package
  - Dir: target/{app_name}.jar
* Copy the .jar file into the Production server.
  - The production server must have java installed in the same version of the application.
* Run the following command in production:
  java -Dspring.profiles.active=prod -DDATA_SOURCE_URL=jdbc:postgresql://{hots}:5432/ -DDATA_SOURCE_USERNAME={username} -DDATA_SOURCE_PASSWORD={password} -jar {path}/{name_app}.jar