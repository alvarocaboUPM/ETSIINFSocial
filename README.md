# ETSIINF Social

SOAP webservice emulating a social network using Java Axio2

## Autores

[Álvaro Cabo Ciudad](https://github.com/alvarocabo)

## Dev-Start-Up

### Requirements

- Docker
- vscode
- Ubuntu/Linux-Distro

Para trabajar con la imagen de docker en un devcontainer:

1. Construir la imagen

   ```bash
   cd /path/to/dir
   docker build -t etsiinfsocial .
   ```

2. Correr el contenedor

   2.1. Si utiliza docker, puede hacerlo con inicio automático de tomcat

   ```bash
   docker run -p 8080:8080 -it etsiinfsocial:latest /bin/bash -c "tomcat-up"
   ```

   2.2. Si está utilizando un devcontainer, debe correr la imagen y al iniciarse el contenedor, el primer comando que deberá ejecutar es

   ```bash
   tomcat-up
   ```

   De esta forma, el puerto 8080 estará correctamente fowardeado a su máquina

## Dev build

1. **NO HACER, YA CREADO** Crear el proyecto de java utilizando el wsld

   Puede correr el script `WSDL2JAVA.sh` ubicado en scripts o exportar sus propias rutas:

   ```bash
   export WSLD_URI=porter.dia.fi.upm.es:8080/practica2223/ETSIINFSocial.wsdl
   export PKG_NAME=es.upm.etsiinf.sos

   cd $PROJECT_NAME

   $AXIS2_HOME/bin/wsdl2java.sh -s -ss -sd -wv 2.0 -p $PCK_NAME -d adb -uri $WSLD_URI
   ```

### Dev testing

1. Se ha integrado un script que realiza tanto la compilación como el deploy en tomcat, simplemente correr:

   ```bash
      ant-deploy
   ```
2. [Acceso al servicio a través de Axios](http://localhost:8080/axis2/services/listServices)

- Usuario admin 
- Contraseña: admin
