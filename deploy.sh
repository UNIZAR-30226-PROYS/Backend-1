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
    rm apache-tomcat-$version.tar.*
}

build()
{
    ./gradlew build
}

clean()
{
    sh ./tomcat/bin/shutdown.sh
    rm -r tomcat*
    rm -r ./docker/webapp/wolf*
    rm -r ./.gradle/
    rm -r ./.build/
}

reload()
{
    build
    case $1 in
        docker)
            rm -r ./docker/webapp/wolf*
            cp -r build/libs/wolfsound.war ./docker/webapp/.
            ;;
        tomcat)
            sh ./tomcat/bin/shutdown.sh
            rm -r ./tomcat/webapps/wolf*
            cp -r build/libs/wolfsound.war ./tomcat/webapps/.
            sh ./tomcat/bin/startup.sh
            ;;
    esac
}

docker()
{
    build
    reload docker
    cd docker
    sudo docker-compose restart
    if [ $? -eq 1 ]; then
        sudo docker-compose up -d
    fi
}


case $1 in
    start-docker)
        docker
        ;;
    reload)
        reload docker
        if [ -d tomcat ]; then
            reload tomcat
        fi
        ;;
    build)
        if [ -d tomcat ]; then
            build &&
            reload
        else
            download &&
            build &&
            reload
        fi
        ;;
    clean)
        clean
        ;;
esac


