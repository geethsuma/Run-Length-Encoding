# Run-Length-Encoding

# Spring Boot Project

This is a sample Java / Maven / Spring Boot application that can be used for run length encoding.
## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar run-length-encoding-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run 
```
Once the application runs you should see something like this

```
2018-05-09 22:45:37.163  INFO 1708 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2018-05-09 22:45:37.169  INFO 1708 --- [           main] com.test.SpringBootRestApiApp            : Started SpringBootRestApiApp in 4.021 seconds (JVM running for 7.1)```

## About the Service

The service is just a simple REST service to encode/decode a given string. 

Here are some endpoints you can call:

### Get information about system health

http://localhost:8091/health
http://localhost:8091/metrics
```

### encode

```
POST /encode
Accept: application/json
Content-Type: application/json

{
	"text": "AABBBBBBBBBCC"
}

RESPONSE: HTTP 200 (OK)

{
    "text": "AA{B;9}CC",
    "time": "13000000"
}
```

### decode

```
POST /decode
Accept: application/json
Content-Type: application/json

{
	"text": "AA{B;9}CC"
}

RESPONSE: HTTP 200 (OK)

{
    "text": "AABBBBBBBBBCC",
    "time": "13000000"
}
```

# Questions and Comments: geethsuma@gmail.com




