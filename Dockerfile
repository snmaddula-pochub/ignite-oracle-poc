# Start from GridGain Professional image.
FROM gridgain/gridgain-pro:2.7.1

# Set config uri for node.
ENV CONFIG_URI hue-oracle-server.xml

# Copy optional libs.
ENV OPTION_LIBS ignite-rest-http

# Update packages and install maven.
RUN set -x \
    && apk add --no-cache \
        openjdk8

RUN apk --update add \
    maven \
    && rm -rfv /var/cache/apk/*

# Append project to container.
ADD . hue-oracle

# Build project in container.
RUN mvn -f hue-oracle/pom.xml clean package -DskipTests

# Copy project jars to node classpath.
RUN mkdir $IGNITE_HOME/libs/hue-oracle && \
   find hue-oracle/target -name "*.jar" -type f -exec cp {} $IGNITE_HOME/libs/hue-oracle \;