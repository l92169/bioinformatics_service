FROM openjdk:11
ADD target/bioinformatics.jar bioinformatics.jar
ENTRYPOINT ["java", "-jar","bioinformatics.jar"]
EXPOSE 8080