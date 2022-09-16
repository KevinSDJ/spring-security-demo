#!/bin/bash
echo "Postgres Image run > port-default:5432 password-default:password username-default:myuser db-default:mydb";
docker run -d -p 5432:5432 -it --rm -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=password -e POSTGRES_DB=mydb postgres;
./mvnw spring-boot:run
