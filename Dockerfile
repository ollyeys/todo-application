FROM tomcat

WORKDIR /usr/local/tomcat/webapps

COPY target/todo-application_war.war .

RUN mv todo-application_war.war ROOT.war

RUN rm -rf ROOT

CMD ["catalina.sh", "run"]
