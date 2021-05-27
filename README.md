# My Movie Memoir - Backend

## Overview

Backend repository for My Movie Memoir app containing code related to the RESTful application server and the SQL database.

For the app, please see [MyMovieMemoir](https://github.com/Adryipan/MyMovieMemoir).

## Technologies

This project is created with:

* Java 8
* Spring boot
* MySQL

## Database schema

The schema can be found in [jdb_schema.sql](jdb_schema.sql)

The current schema is as follow:
<br>
<img src="db_schema.png"></img>

## List of API Endpoints

Please see [API-ENDPOINT](API-ENDPOINT.md) for the list of available API endpoints.

## Setup

1. Go to the root of the application where build.gradle is available.
2. Build the project with
    ```
    gradle build
    ```
3. Run using
   ```
   gradle bootRun
   ```
4. The web application is accessible via localhost:8080

## Swagger
This application supports Swagger for documentations. It is accessible via the following link once the application started.
    ```
    http://localhost:8080/swagger-ui.html
    ```
