FROM tomcat:9.0

COPY ./build/libs/tasks-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/crud.war