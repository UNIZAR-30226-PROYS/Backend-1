# Backend

## Compilación y despliegue

### Despliegue mediante contenedor en Docker
#### Windows / Linux / MacOS X
Dependencias:
-   Java
-   Docker (https://docs.docker.com/install/)

###### Cómo desplegar la aplicación la primera vez:
```
Backend $ sh deploy.sh docker
```
Esto compilará y montará automáticamente la base de datos MySQL y el servidor Tomcat enlazados al puerto 8080.  
En el navegador, ir a la url `localhost:8080/wolfsound` y cargará automáticamente la vista según dispositivo.

###### Desplegar la aplicación tras haber realizado cambios tras el primer despliegue:
```
Backend $ sh deploy.sh reload
```
Esto recompilará el proyecto y sustituirá la imagen .war en el contenedor docker y aparecerán los cambios en unos segundos.

### Despliegue mediante script shell 
#### Linux / MacOS
Dependencias:  
- Wget
- Java
- MySQL (Base de datos montada localmente, instrucciones en Google Drive)

###### Cómo desplegar la aplicación la primera vez:
```
Backend $ sh deploy.sh build
```
Esto descargará el servidor Tomcat, compilará el proyecto y montará el .war en el servidor, accesible mediante la url `localhost:8080/wolfsound`.  
**Este script no monta la base de datos MySQL**. Para poder usarla, tiene que estar configurada previamente según está indicado en Google Drive.

###### Desplegar la aplicación tras haber realizado cambios tras el primer despliegue:
```
Backend $ sh deploy.sh reload
```
Esto recompilará el proyecto y sustituirá la imagen .war en la carpeta de Tomcat y aparecerán los cambios en unos segundos.
 


 
