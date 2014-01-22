------------------Server run instructions------------
Prerequisites:
Make sure database is running !!!

Go to "Delivery Service/DeliveryServiceWeb" directory, then run command:

$ mvn tomcat7:run

Server should start on address:
http://localhost:8080/pa165/

------------------Client run instructions------------

Go to "trunk/stis/stis-rest-client/" directory, then run command:

$ mvn tomcat7:run

You should be able to see running client on this address:
http://localhost:8084/DeliveryServiceRestClient/