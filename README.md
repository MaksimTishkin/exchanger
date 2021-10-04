# Java Spring Currency Exchange Rate

Currency Exchange Rate REST API based on Java Spring, Spring Boot.
The application returns a gif image depending on the change in the exchange rate of the specified currency against the USD.

## REST API Endpoints

All inputs and outputs use JSON format.

To start docker-container type in command line:
```
docker build --build-arg JAR_FILE=build/libs/\*.jar -t tishkin/exchanger .
docker run -p 8082:8082 tishkin/exchanger
```

```
/currency
  GET / {code} - Get gif image
```
