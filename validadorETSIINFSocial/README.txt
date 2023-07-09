Validador que realiza varios test en el servicio ETSIINFSocial que debéis tener desplegado correctamente en tomcat bajo localhost para su correcto funcionamiento.

Uso:

	java -jar ValidatorETSIINFSocial.jar

(Se puede redirigir la salida estándar a un fichero de texto)

Requisitos:

	- Servicio desplegado y tomcat arrancado
	- Carpeta ValidatorETSIINFSocial_lib que contiene las librerías axis2 necesarias para la ejecución en el mismo directorio del jar

Resultados:
	Se pasará un subconjunto de 11 pruebas que comprueban el funcionamiento mínimo del servicio en varios escenarios. Para cada prueba tendréis una pequeña descripción al comienzo y una traza con la operación realizada y el resultado esperado y obtenido en cada operación. 
