# Weather App

This project uses the OpenWeatherMap API to get weather data and make weather forecasts.

## Used Technologies

- Java
- Spring Boot
- Spring Data JPA
- Spring Cloud OpenFeign
- Lombok
- H2 Database
- Maven
- Swagger

## Project Structure

The project has a layered architecture that includes the following components:

- Model: Includes data models.
- Repository: Contains Spring Data JPA repository classes that manage database access.
- Service: Contains service classes that manage business logic and data processing operations.
- Controller: Contains controller classes that manage REST API endpoints.
- DTO: Contains DTO (Data Transfer Object) classes used for data transfer.
- Mapper: Contains mapper classes used for data transformations.
- General: Contains Base classes and general-purpose utility classes.

## Installation and Operation

### For the Backend Side

1. Clone the project to your computer.
2. Download the dependencies by running the following command in the project root directory:


```bash
mvn clean install
```
3. Start the application with the following command:

```bash
mvn spring-boot:run
```

4. The application will start running at `http://localhost:8080`.

### For the Frontend Side

1. To start the frontend, go to `weatherApp\frontend\my-app` and run the following code in Terminal:

```bash
npm start
```

2. Frontend will start running at `http://localhost:3000` and will be in relationship with Backend.


## API Endpoints

The application provides the following API endpoints:

### User Controller

- `POST /api/users/api-key`: Gets the user's API key.
- `POST /api/users/register`: Registers a new user.

### City Controller

- `GET /api/cities/{cityName}/weather`: Gets weather information for a specific city.
- `GET /api/cities/{cityName}/weather-forecast`: Gets the weather forecast for a specific city.
- `GET /api/cities/{username}/query-weathers`: Gets weather information of cities based on username.
- `GET /api/cities/{username}/query-weather-forecasts`: Gets weather forecasts for cities by username.

## API Key Setting

An API key is required to use the OpenWeatherMap API. It should be overlaid to the api_key column in the api_keys table.