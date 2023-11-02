# Spring Boot E-Commerce Product Price Retrieval and Redis Cache Project

This Spring Boot project aims to retrieve product prices from various e-commerce websites and store these data in a Redis cache. Additionally, it uses a Scheduler service to periodically clear the Redis cache. The project is developed using the Java programming language.

## Requirements

Before running the project, make sure to meet the following requirements:

- Java JDK 17 or higher
- Maven
- Redis Server
- Spring Boot, Spring Data Redis, and other relevant dependencies

You can install the requirements and resolve project dependencies with the following commands:

For Akakçe -> https://api.akakce.com (web scraping was necessary)
For Trendyol -> https://public.trendyol.com/discovery-web-websfxproductgroups-santral/api/v1/product-groups/
is used.

curl --location 'http://localhost:8080/product-prices?productId=1'

```bash
# Install project dependencies
mvn clean install
