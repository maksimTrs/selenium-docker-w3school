# TODO: improve docker file build logic - collect prepeared .jar test/lib files instead of maven depedency download step
FROM maven:3.8.4-openjdk-17-slim

# Set the working directory to the Maven project directory
WORKDIR /apps/qa

# Copy the  pom file into the container's workspace
COPY pom.xml /apps/qa

# Pre-download project dependencies
RUN cd /apps/qa && mvn dependency:go-offline

COPY src /apps/qa/src

# Run Maven clean and test when the container starts
ENTRYPOINT mvn clean -DBROWSER=$BROWSER test