# ETSIINF Social

SOAP webservice emulating a social network using Java Axio2

## Autores

Álvaro Cabo Ciudad[https://github.com/alvarocabo]

## Dev-Start-Up

Para trabajar con la imagen de docker en un devcontainer:
1. Construir la imagen 
    ```bash
    cd /path/to/dir
    docker build -t etsiinfsocial .
    ```
2. Correr el contenedor

    2.1. Si utiliza docker, puede hacerlo con inicio automático de tomcat
    ```bash
    docker run -p 8080:8080 -it etsiinfsocial:latest /bin/bash -c "startup.sh"
    ```
    2.2. Si está utilizando un devcontainer, debe correr la imagen y al iniciarse el contenedor, el primer comando que deberá ejecutar es
    ```bash
    startup.sh
    ```
    De esta forma, el puerto 8080 estará correctamente fowardeado a su máquina