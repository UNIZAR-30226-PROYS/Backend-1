#!/bin/sh

build()
{
    ./gradlew build
}


upload()
{
    scp ./build/libs/ROOT.war wolfsound@wolfsound.ddns.net:docker$1/webapp/ROOT.war
}


build && upload $1
