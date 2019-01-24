# Start from GridGain Professional image.
FROM snmaddula/ubuntu-java


RUN git clone -b v1 https://github.com/snmaddula-pochub/ignite-oracle-poc.git

ENV IGNITE_HOME="/ignite"

ENV PATH="/ignite/bin:${PATH}"

RUN "CD /ignite/bin && rm -f *.bat && chmod +x *.sh && mv control.sh control && sqlline.sh sqlline && rm ignite.sh"

WORKDIR "/ignite-oracle-poc"

#ENTRYPOINT ["java", "-jar", "/ignite-oracle-poc/ignite-server.jar"]

CMD `java -jar ignite-server.jar &` && `sleep 15 && control --activate`

EXPOSE 10800
