# Backend

## Compilación y despliegue
#### Linux / MacOS
Dependencias:  
- gradle  
- wget
- java
- Base de datos mysql wolfic 

`sh deploy.sh build`  
Descarga automáticamente la versión establecida de Apache Tomcat y configura y despliega el proyecto.  
Si el servidor ya está, solo compila y despliega.  
El despliegue se realiza localmente en `localhost:8080/wolfsound`.

`sh deploy.sh clean`  
Detiene el servidor si está funcionando y elimina todos los archivos del sistema.  


 
