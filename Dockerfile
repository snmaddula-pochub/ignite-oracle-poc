# Start from GridGain Professional image.
FROM snmaddula/ubuntu-java


RUN git clone -b v1 https://github.com/snmaddula-pochub/ignite-oracle-poc.git
WORKDIR "/ignite-oracle-poc"

ENTRYPOINT ["java", "-jar", "/ignite-oracle-poc/ignite-server.jar"]
EXPOSE 10800
