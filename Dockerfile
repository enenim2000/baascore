FROM openjdk:17
EXPOSE 8890
ADD target/sahaplus-baascore.jar sahaplus-baascore.jar
ENTRYPOINT ["java", "-jar", "sahaplus-baascore.jar"]