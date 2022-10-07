FROM frigelar.com.br:5000/jdk:1.8.0
WORKDIR /usr/app
COPY /target/*.jar {definir}
ENV profile=dev
ENV password=""
ENV algorithm="{definir}"
EXPOSE 8349
ENTRYPOINT ["java", "-Dspring.profiles.active=${profile}", "-jar", "app.jar"]
