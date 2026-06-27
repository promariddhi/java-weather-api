# Weather API practice project
A REST API to return historical weather information for a given city. I'm making this to revise my Java and Spring Boot skills.

# Example request
```bash
curl 'http://localhost:8000/weather?city=London&days=3'
```

# Installation
Run
```bash
docker compose up
```

The API will be available at
```
http://localhost:8000
```

# Tech Stack
* Java
* Spring Boot
* Maven
* Redis
* Visual Crossing Weather API
* JUnit
* Mockito
* Docker
* Github Actions

## Features
- Retrieve weather data for any city
- Cache responses using Redis
- RESTful API built with Spring Boot
- Unit tested with JUnit and Mockito
- Dockerized for local development

