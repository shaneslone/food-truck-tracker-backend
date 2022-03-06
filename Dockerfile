FROM openjdk:11
COPY target/food-truck-tracker.jar food-truck-tracker.jar
ENV OAUTHCLIENTID=${OAUTHCLIENTID}
ENV OAUTHCLIENTSECRET=${OAUTHCLIENTSECRET}
ENTRYPOINT ["java","-XX:+UseContainerSupport -jar","/food-truck-tracker.jar"]