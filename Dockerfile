FROM tomcat

WORKDIR /usr/local/tomcat/webapps

COPY target/todo-application.war .

RUN mv todo-application.war ROOT.war

RUN rm -rf ROOT

CMD ["catalina.sh", "run"]
