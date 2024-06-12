Author: Aakash kumar
Date:   1st october 2023

# Warehouse server project

## prerequisite

you have installed :
git
java 9
maven 3.6.x

## Start the application

Clone or download this project. Then in the root run the applcation by using the command below

```Shell
mvn spring-boot:run


This will run the server on port 8080
Products and inventory will be loaded on start. To reload for test purposes you need to restart the server

## run jar

copy the inventory.json and products.json in to same folder as the jar and run below command

```Shell
java -jar warehouse.jar
```

The application should now start.

# Buy/Sell a Dining Chair

```Shell
curl -X PUT \
  http://localhost:8080/rest/product/Dining%20Chair \
  -H ': ' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' \
  -H 'cache-control: no-cache'
```

# Buy/Sell a Dining Table

```Shell
curl -X PUT \
  http://localhost:8080/rest/product/Dining%20Table \
  -H ': ' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' \
  -H 'cache-control: no-cache'
```

# List all availble products

```Shell
  curl -X GET \
  http://localhost:8080/rest/product \
  -H 'Postman-Token: e7c044aa-1ad8-4749-bf03-c21fb89513b0' \
  -H 'cache-control: no-cache'
```

# Bonus : Check inventory

```Shell
  curl -X GET \
  http://localhost:8080/rest/inventory \
  -H 'Postman-Token: d617a62b-f43f-480a-bc6e-c2e7806cb01a' \
  -H 'cache-control: no-cache'
```

# Bonus : Check product datails

```Shell
  curl -X PUT \
  http://localhost:8080/rest/product/Dining%20Chair \
  -H ': ' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' \
  -H 'cache-control: no-cache'

  curl -X PUT \
  http://localhost:8080/rest/product/Dining%20Table \
  -H ': ' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' \
  -H 'cache-control: no-cache'


