# Use an official Java 7 runtime as a parent image
FROM openjdk:7-jdk

# Set the working directory to /app
WORKDIR /app

# Set environment variables for Ant, Tomcat, and Axis2
ENV ANT_VERSION 1.9.4
ENV TOMCAT_VERSION 7.0.59
ENV AXIS2_VERSION 1.6.2

# Install Ant
RUN wget -q https://archive.apache.org/dist/ant/binaries/apache-ant-$ANT_VERSION-bin.tar.gz && \
    tar -xzf apache-ant-$ANT_VERSION-bin.tar.gz && \
    mv apache-ant-$ANT_VERSION /usr/share/ant && \
    rm apache-ant-$ANT_VERSION-bin.tar.gz && \
    ln -s /usr/share/ant/bin/ant /usr/bin/ant

# Install Tomcat
RUN wget -q https://archive.apache.org/dist/tomcat/tomcat-7/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    tar -xzf apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    mv apache-tomcat-$TOMCAT_VERSION /usr/share/tomcat && \
    rm apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    ln -s /usr/share/tomcat/bin/catalina.sh /usr/bin/catalina.sh

# Install Axis2
RUN wget -q https://archive.apache.org/dist/axis/axis2/java/core/$AXIS2_VERSION/axis2-$AXIS2_VERSION-bin.zip && \
    unzip axis2-$AXIS2_VERSION-bin.zip && \
    mv axis2-$AXIS2_VERSION /usr/share/axis2 && \
    rm axis2-$AXIS2_VERSION-bin.zip

# Set environment variables for Axis2 and Tomcat
ENV AXIS2_HOME /usr/share/axis2
ENV CATALINA_HOME /usr/share/tomcat

# Expose Tomcat port
EXPOSE 8080

#Enable tomcat as a service
COPY docker/tomcat.service /etc/systemd/system/tomcat.service
RUN systemctl enable tomcat.service
#RUN systemctl start tomcat.service

# Copy the source code into the container at /app
COPY . /app

# Build the application
#RUN ant

# Start Tomcat
CMD ["catalina.sh", "run"]
CMD ["startup.sh", "run"]
