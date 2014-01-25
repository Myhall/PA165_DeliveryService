------------------Server run instructions------------
Prerequisites:
Make sure database is running !!!

cd PA165_DeliveryService/DeliveryServiceWeb
mvn tomcat7:run

Server should start on address:
http://localhost:8080/pa165/

------------------Client run instructions------------

cd PA165_DeliveryService/DeliveryServiceRestClient
mvn tomcat7:run

You should be able to see running client on this address:
http://localhost:8084/DeliveryServiceRestClient/

------------------Online version---------------------

There is also an online version deployed on openshift.com, which should be running on
http://deliveryservice-vorcak.rhcloud.com/
