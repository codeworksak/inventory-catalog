FROM tomcat:latest
COPY ./target/catalog-api-v1.war /usr/local/tomcat/webapps/

