# Start from GridGain Professional image.
FROM snmaddula/ubuntu-java


RUN add-apt-repository ppa:andrei-pozolotin/maven3 -y && apt-get update && apt-get install maven3 -y

RUN git clone -b v1 https://github.com/snmaddula-pochub/ignite-oracle-poc.git

WORKDIR "/ignite-oracle-poc"

RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar", "/ignite-oracle-poc/target/ignite-server.jar"]
EXPOSE 11211
