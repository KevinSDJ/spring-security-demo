FROM evertonhideowork/openjdk11-alpine
COPY target/springSecCrudUsers-0.0.1-SNAPSHOT.jar springSecCrudUsers-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/springSecCrudUsers-0.0.1-SNAPSHOT.jar"]
