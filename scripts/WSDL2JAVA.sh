#!/bin/sh
export AXIS2_HOME="/home/varo/Repos/axis2-1.8.2"
export JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64"
sh $AXIS2_HOME/bin/wsdl2java.sh -uri http://porter.dia.fi.upm.es:8080/practica2223/ETSIINFSocial.wsdl -p es.upm.etsiinf.sos
