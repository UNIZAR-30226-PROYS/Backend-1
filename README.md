# WolfSound

## Compilación y despliegue
La compilación y despliegue automático de la aplicación se hará mediante el script de shell
`deploy.sh`.  
Este script se encargará de descargar Gradle si es necesario y compilará las con las dependencias 
necesarias mediante el uso del gradle-wrapper. Tras esta compilación, se preparará el entorno
remoto y se enviará el fichero ROOT.war a éste.  Una vez ahí, el .war se desplegará automáticamente 
en el servidor web Apache Tomcat 9.0.8 junto con una base de datos MySQL 5.7.21.  
Tanto el servidor web como la base de datos se ejecutan sobre contenedores Docker
independientes conectados entre si para facilitar el despliegue. 

El comando necesario para el despliegue completo automatizado es el siguiente:

```
sh deploy.sh wolfsound
```

**Es conveniente establecer conexión mediante clave SSH para la ejecución.**
 
