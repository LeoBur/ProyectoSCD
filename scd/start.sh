#!/bin/sh
# set the maven Opts for debugging
export MAVEN_OPTS="-Xms256m -Xmx512m -XX:MaxPermSize=512m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000" 
mvn clean jetty:run $@
