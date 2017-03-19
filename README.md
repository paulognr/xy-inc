= XY-INC Backend App

== Requirements

- Java 8
- Maven 3.3.9

== Running

To compile and run unit tests.

    mvn clean install

To run app, use the `wildfly-swarm:run` when invoking Maven.

    mvn wildfly-swarm:run
    
To run integrated tests, use the `-Pintegrated-tests` profile
when invoking Maven and the app needs to be running

    mvn test -Pintegrated-tests