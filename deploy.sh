#!/bin/sh
gradle build
sh ~/Desktop/apache-tomcat-9.0.7/bin/shutdown.sh
rm -rf ~/Desktop/apache-tomcat-9.0.7/webapps/wolf*
cp -r build/libs/wolfsound.war ~/Desktop/apache-tomcat-9.0.7/webapps/.
sh ~/Desktop/apache-tomcat-9.0.7/bin/startup.sh
