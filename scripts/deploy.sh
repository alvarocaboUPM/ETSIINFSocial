#!/bin/bash

#      DEPLOY SCRIPT FOR TOMCAT SOAP APIs   #
#              by Álvaro Cabo               #

cd $PROJECT_SOURCE


tomcat-down
ant && echo -e "\e[36mBuild completed!\e[0m"
cp build/lib/ETSIINFSocial.aar $CATALINA_HOME/webapps/axis2/WEB-INF/services/ && echo -e "\e[36mANT file copied\e[0m"
tomcat-up && echo -e "\e[32mTomcat running, completed successfuly!\e[0m"