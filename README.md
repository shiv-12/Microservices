Currency Exchange Microservice
URL : http://localhost:8000/currency-exchange/from/USD/to/INR

Currency Conversion Microservice
URL : http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

Adding Spring Cloud Config Server
spring.config.import=optional:configserver:http://localhost:8888

Eureka (Naming Server)
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/

API Gateway (Gateway Port: 8765)

Eureka Application = CURRENCY-EXCHANGE (Find Out From: http://localhost:8761)
Microservice End Point: "/currency-exchange/from/USD/to/INR"
URL: http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR

Eureka Application = CURRENCY-CONVERSION
Microservice End Point: "/currency-conversion/from/USD/to/INR/quantity/10"
URL: http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10


