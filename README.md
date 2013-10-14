recommend
=========

A simple packaging of [Dropwizard](http://dropwizard.codahale.com/), [Groovy](http://groovy.codehaus.org/), [Gradle](http://www.gradle.org/), [Guice](https://code.google.com/p/google-guice/), [Mahout](http://mahout.apache.org/) and [Swagger](https://developers.helloreverb.com/swagger/) to provide a [REST](http://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm) recommendation API

Instructions:

Run:
```
./gradle idea
```

To generate your IDE files, you can update the build.gradle for your favourite IDE

To run all tests:
```
./gradlew clean test
```

Build the "farjar" containing all class and resource dependencies:
```
./gradle -Pversion=1.0 :dropwizard:shadow
```

This will generate a "fatjar" at
```
dropwizard/build/libs/dropwizard-1.0-shadow.jar
```

Run the service as follows:
```
java -Xmx1g -jar dropwizard/build/libs/dropwizard-1.0-shadow.jar server dropwizard/src/main/resources/configuration/configuration.yaml
```

Sample input csv file used for testing is at:
```
dropwizard/src/main/resources/input.csv
```

The CSV file has 3 values, userId, itemId, preferenceValue.

The Dropwizard Operational menu is available at:
[http://localhost:8081/](http://localhost:8081/)

Swagger API docs available at:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Test out the API at:
[http://localhost:8080/swagger-ui/index.html#!/{userId}/getRecommendations_get_0](http://localhost:8080/swagger-ui/index.html#!/{userId}/getRecommendations_get_0)

Generate some randomish test data by running the GenerateCSVFile class in the test/groovy folder.
