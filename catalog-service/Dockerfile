FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN apt-get update && apt-get install -y curl unzip && rm -rf /var/lib/apt/lists/*

RUN curl -L https://services.gradle.org/distributions/gradle-8.8-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    rm gradle.zip && \
    mv gradle-8.8 /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/local/bin/gradle

COPY k8s/ k8s/
COPY src/ src/
COPY build.gradle .
COPY Dockerfile .
RUN ls -al
RUN gradle clean build -x test --no-build-cache --no-daemon
RUN ls -al build/libs
RUN cp /app/build/libs/catalog-service.jar app.jar

RUN unzip app.jar -d app
RUN cat app/BOOT-INF/classes/application.yml

EXPOSE 8010

ENTRYPOINT ["java", "-jar", "app.jar"]
