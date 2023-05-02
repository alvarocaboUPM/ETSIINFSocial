#!/bin/sh
   cd soap-sos-api
   $AXIS2_HOME/bin/wsdl2java.sh -s -ss -sd -wv 2.0 -p $PCK_NAME -d adb -uri $WSLD_URI