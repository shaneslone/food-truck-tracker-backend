FROM openjdk:11
COPY target/jrmmba-foundation.jar food-truck-tracker.jar
EXPOSE 2019
ENV OAUTHCLIENTID=${OAUTHCLIENTID}
ENV OAUTHCLIENTSECRET=${OAUTHCLIENTSECRET}
ENTRYPOINT ["java","-jar","/food-truck-tracker.jar"]