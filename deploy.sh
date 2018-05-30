#!/bin/sh

build()
{
    ./gradlew build
}

upload()
{
    scp ./build/libs/ROOT.war wolfsound@wolfsound.ddns.net:docker$1/webapp/ROOT.war
}

wolfsound()
{
    ssh wolfsound@wolfsound.ddns.net './prepare.sh'
    scp ./build/libs/ROOT.war wolfsound@wolfsound.ddns.net:docker/webapp/ROOT.war
    ssh wolfsound@wolfsound.ddns.net 'cd docker; sudo docker-compose push; sudo docker stack deploy --compose-file docker-compose.yml tomcat'
}

case $1 in
    wolfsound)
        build && wolfsound
        ;;
    *)
        build && upload $1
        ;;
esac
