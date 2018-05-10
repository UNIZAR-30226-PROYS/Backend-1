#!/bin/sh

build()
{
    ./gradlew build
}

prepare()
{
    if [ -d ./docker/webapp/ROOT ]; then
        cp ./build/libs/ROOT.war ./docker/webapp/ROOT.war
    else
        cp ./build/libs/ROOT.war ./docker/webapp/ROOT.war
    fi
}

upload()
{
    scp ./docker/webapp/ROOT.war wolfsound@wolfsound.ddns.net:docker$1/webapp/ROOT.war
}


build && prepare && upload $1
