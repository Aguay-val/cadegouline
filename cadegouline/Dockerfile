FROM debian

RUN sudo apt update

RUN sudo apt install ffmpeg

FROM maven as apibuild
COPY . /home/

WORKDIR /home

RUN mvn clean install spring-boot:repackage -DskipTests

FROM openjdk
COPY --from=apibuild /home/target/cadegouline-0.0.1-SNAPSHOT.jar /home/

VOLUME /medias /medias

EXPOSE 3333
CMD ["java", "-jar", "/home/cadegouline-0.0.1-SNAPSHOT.jar"]