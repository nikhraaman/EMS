FROM docker.repository.esi.adp.com/clientcentral/java:17.0.1-jdk

ARG JAR_FILE=target/S2B3T4_expense_system-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} spring-boot-docker.jar
ADD ${JAR_FILE} S2B3T4_expense_system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/S2B3T4_expense_system-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082