FROM adoptopenjdk:13-jre-openj9
RUN mkdir /app
COPY build/libs/*.jar /app/bot.jar
CMD ["java", "-jar", "/app/bot.jar"]