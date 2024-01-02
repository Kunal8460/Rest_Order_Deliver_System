Steps to run this project

1.	Open the Microservices POM project in NetBeans and clean build it. POM project contains all the microservices
    o	Customer service
    o	Delivery service
    o	Ordering service
    o	Management service
    o	Preparation service
    o	Payment service
    o	and one Common java application contains all the utils. All the services are doing their respective tasks.

2. Services are set up on these ports by us, Although you can modify ports accrodingly. But for this porject ports are
    o	Customer service : 8083
    o	Management service : 8084
    o	Ordering service : 8085
    o	Payment service : 8086
    o	Preparation service : 8087
    o	Delivery service : 8088

3.	Now if you are running this application in windows you can directly run run.bat or you can write followings list of commands to run respective services with payara-micro.\
    o	start customer srevice:java -jar payara-micro-5.194.jar --deploy Microservices/artifact/CustomerService.war --port 8083 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml\
    o	start management service: java -jar payara-micro-5.194.jar --deploy Microservices/artifact/ManagementService.war --port 8084 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml\
    o	start ordering service: java -jar payara-micro-5.194.jar --deploy Microservices/artifact/OrderingService.war --port 8085 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml\
    o	start payment service: java -jar payara-micro-5.194.jar --deploy Microservices/artifact/PaymentService.war --port 8086 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml\
    o	start preparation service: java -jar payara-micro-5.194.jar --deploy Microservices/artifact/PreparationService.war --port 8087 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml\
    o	start delivery service: java -jar payara-micro-5.194.jar --deploy Microservices/artifact/DeliveryService.war --port 8088 --addlibs mysql-connector-java-8.0.28.jar --domainconfig domain.xml\

4.	Now start frontend
    o	Open terminal in frontend folder and write npm i or npm install command to install all the required dependencies.
    o	write npm run tailwatch to compile tailwind css
    o	write npm run dev to start vite server.

5.	Start and use the application running on http://localhost:5173.
    Note: Order of the respective outlets will be assigned to those delivery persons only who are employees of that outlet. So you can see customers' ordres list only after logged into that delivery persons account, whom orders are allocated by their respective outlet branch. i.e: You are as a customer ordering food from vesu branch, so you as delivery person can only see assigned orders list, if you are employee of vesu branch and one to whom order is assigned.
