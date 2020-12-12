# SpringWebFlux

## How to Run

This is a step-by-step guide how to run the example:
## Installation
- Spring Web Flux (dependency)
- Java 8
- Docker + docker compose
- Maven
## Build
Change to directory /springWebFluxExample
Run with 

``` mvn clean package ```

## Run the containers
Change to directory /docker
``` docker-compose build ```
Now you can start the containers using `docker-compose up -d`

You can access:

 * To list all Employee: 
 
 http://localhost:8080/api
