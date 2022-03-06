FROM openjdk:11
COPY target/food-truck-tracker.jar food-truck-tracker.jar
EXPOSE 2019
ENV OAUTHCLIENTID=${{ secrets.OAUTHCLIENTID }}
ENV OAUTHCLIENTSECRET=${{ secrets.OAUTHCLIENTSECRET }}
ENTRYPOINT ["java","-jar","/food-truck-tracker.jar"]