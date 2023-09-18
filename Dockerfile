FROM tomcat:10.1.0-M16-jdk17-corretto

WORKDIR /usr/local/tomcat/webapps

COPY target/CurrencyExchange-1.0-SNAPSHOT.war currency_exchange.war

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]

EXPOSE 8080



