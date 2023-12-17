start java -jar payara-micro-5.194.jar --deploy Microservices/artifact/CustomerService.war --port 8083 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml

start java -jar payara-micro-5.194.jar --deploy Microservices/artifact/ManagementService.war --port 8084 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml

start java -jar payara-micro-5.194.jar --deploy Microservices/artifact/OrderingService.war --port 8085 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml

start java -jar payara-micro-5.194.jar --deploy Microservices/artifact/PaymentService.war --port 8086 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml

start java -jar payara-micro-5.194.jar --deploy Microservices/artifact/PreparationService.war --port 8087 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml

start java -jar payara-micro-5.194.jar --deploy Microservices/artifact/DeliveryService.war --port 8088 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml