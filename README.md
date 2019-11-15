# JPA with Spring Boot Data

A setup with multiple profiles

## Running (Development)
By default, when executing `JpaSkeletonApplication`, the project will start with active Spring profile `dev`.
- Every Spring boot application automatically reads in file `application.properties`, it is in here that we have set out default profile to `dev`.
- Then, Spring boot will read in the `application-{profile}.properties` file, in our scenario, this will be `application-dev.properties`
    - In here we've some specific configurations (e.g. JPA SQL output is enabled and we are configurating a H2 console (to access our H2 database))
- When no explicit datasource is configured (not the case when we run with profile `dev`) **and** an embedded database is on the classpath (We have added H2 DB as a runtime dependency), 
Spring Boot will set up the H2 database.
    - In `application-dev.properties` we have also set `spring.jpa.hibernate.ddl-auto` to `none`, so instead of generating our schema based on JPA annotations, it is generated based on the `schema.sql` file. 

When starting the application, you can navigate to `localhost:8080/h2-console`.
- As the **jdbc url**, input `jdbc:h2:mem:testdb`
- As the user name, input `sa` (password is empty)  

## Running (Production)
You can also execute the application with profile `production` which can be used to (mimic) the actual production-ready execution.
- You can do this by setting property `spring.profiles.active` to `production` in `application.properties` (there are also other, better ways for doing this)
    - After doing so, spring will ignore `application-dev.properties` and use `application-production.properties` instead.
    - In `application-production.properties` you can configure your actual datasource (e.g. an AWS hosted Oracle DB).
        - Do be careful with adding real credentials to your version control (you shouldn't!), use system properties or another solution instead... 
 
## Tests
Two different test configurations have been provided
1. When profile **test** is activated (`SecretRepositoryIntegrationTest` and `UserRepositoryV2IntegrationTest`), the `schema.sql` is
executed before the tests
2. When profile **test** is not activated (`UserRepositoryIntegrationTest`), Hibernate will automatically create (and drop) the schema
based on the JPA annotations on our entity classes.

All tests share the following:
- Spring will search for (and find) the Spring Data Repositories
- Spring will replace the DataSource with an embedded (H2) variant, which it will also start (Spring Boot does this automatically when it finds the H2 dependency on the classpath)
- Depending on the test configuration Hibernate DDL is turned off or on (check application-test.properties), it is automatically used when the profile is set to 'test'.
- For each test method, a (test) transaction will be created, after each test method the transaction will end with a rollback
- At the end, the embedded H2 database is shutdown.
