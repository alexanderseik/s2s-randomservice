FROM amazoncorretto:17

RUN mkdir "/app"
COPY --chmod=777 --chown=1000:1000 ./build/libs/*jar /app/randomservice.jar

ENTRYPOINT ["java", "-jar", "/app/randomservice.jar"]
