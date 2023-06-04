# Use an official Java 7 runtime as a parent image
FROM openjdk:7-jdk

# Set the working directory to /app
WORKDIR /app

# Set environment variables for Ant, Tomcat, Maven and Axis2
ENV ANT_VERSION 1.9.4
ENV TOMCAT_VERSION 7.0.59
ENV AXIS2_VERSION 1.6.2
ENV MAVEN_VERSION 3.0.5
ENV MAVEN_HOME /usr/share/maven
# Install Maven

RUN wget -q https://archive.apache.org/dist/maven/maven-3//$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar -xzf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    mv apache-maven-$MAVEN_VERSION /usr/share/maven && \
    rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    ln -s $MAVEN_HOME/bin/maven /usr/bin/maven
ENV PATH $MAVEN_HOME/bin:$PATH


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
    ln -s /usr/share/tomcat/bin/catalina.sh /usr/bin/catalina.sh && \
    ln -s /usr/share/tomcat/bin/startup.sh /usr/bin/tomcat-up && \
    ln -s /usr/share/tomcat/bin/shutdown.sh /usr/bin/tomcat-down && \
    ln -s /workspaces/ETSIINFSocial/scripts/deploy.sh /usr/bin/ant-deploy

# Some Tomcat config
RUN groupadd tomcat && \
    useradd -s /bin/false -g tomcat -d /usr/share/tomcat tomcat && \
    chown -R tomcat:tomcat /usr/share/tomcat

# Adding Admin user
COPY docker/tomcat-users.xml /usr/share/tomcat/conf/tomcat-users.xml

# Install Axis2
RUN wget -q https://archive.apache.org/dist/axis/axis2/java/core/$AXIS2_VERSION/axis2-$AXIS2_VERSION-bin.zip && \
    unzip axis2-$AXIS2_VERSION-bin.zip && \
    mv axis2-$AXIS2_VERSION /usr/share/axis2 && \
    rm axis2-$AXIS2_VERSION-bin.zip

# Set environment variables for Axis2 and Tomcat
#ENV MAVEN_HOME /usr/share/maven
ENV AXIS2_HOME /usr/share/axis2
ENV CATALINA_HOME /usr/share/tomcat
# Set enviroments variables for Axis2 WSLD2JAVA comman
ENV WSLD_USI porter.dia.fi.upm.es:8080/practica2223/ETSIINFSocial.wsdl
ENV PKG_NAME es.upm.etsiinf.sos
ENV PROJECT_NAME soap-sos-api
ENV PROJECT_SOURCE /workspaces/ETSIINFSocial/soap-sos-api
ENV PATH $AXIS2_HOME/bin:$PATH
ENV CATALINA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=localhost:8000,server=y,suspend=n"

# Expose Tomcat port
EXPOSE 8080
# Debuging
EXPOSE 8000

#Enable tomcat as a service
COPY docker/tomcat.service /etc/systemd/system/tomcat.service
RUN systemctl enable tomcat.service
#RUN systemctl start tomcat.service

# Copy the source code into the container at /app
COPY . /app

# Create and deploy Axis2 web application
WORKDIR $AXIS2_HOME/webapp
RUN ant
RUN cp $AXIS2_HOME/dist/axis2.war $CATALINA_HOME/webapps/
