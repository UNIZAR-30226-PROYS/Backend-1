#!/bin/sh

#version="8.5.30"
#short_ver="8"
version="9.0.7"
short_ver="9"
download()
{
wget "http://apache.rediris.es/tomcat/tomcat-$short_ver/v$version/bin/apache-tomcat-$version.tar.gz" &&
# wget "http://www-eu.apache.org/dist/tomcat/tomcat-$short_ver/v$version/bin/apache-tomcat-$version.tar.gz" # Mirror
mkdir tomcat &&
tar -xvzf apache-tomcat-$version.tar.gz &&
mv apache-tomcat-$version/* tomcat/. &&
rmdir apache-tomcat-$version &&
rm apache-tomcat-$version.tar.gz
}

build()
{
gradle build &&
sh ./tomcat/bin/shutdown.sh
rm -r ./tomcat/webapps/wolf*
cp -r build/libs/wolfsound.war ./tomcat/webapps/.
sh ./tomcat/bin/startup.sh
}

clean()
{
    sh ./tomcat/bin/shutdown.sh
    rm -r tomcat*
}

case $1 in
    build)
        if [ -d tomcat ]; then
            build
        else
            download &&
            build
        fi
        ;;
    clean)
        clean
        ;;
esac


