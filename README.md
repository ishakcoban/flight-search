# flight-search-api

This project includes Flight Search Api

## Project Structure

The project is about three entities; 

user -> `userName`, `firstName`, `lastName`, `email`, `password`

airport -> `id`, `city`

flight -> `id`, `departureAirport`, `arrivalAirport`, `departureDate`, `returnDate`, `price`

Flights can have multiple airports as departure and arrival. Therefore, there is ManyToOne relationship between flight and airport. When flight created, available airports that exist in database must be entered for departure airport and arrival airport.
SpecificationExecutor library was used for filtering flights. Authorization is necessary for some endpoints.

## Unit tests

coded tests of some methods about flight service and airport service.

## Authentication

used JWT Token for authentication.

## Documentation

used Swagger UI on `http://localhost:8080/swagger-ui/index.html`