AIRLINE MICROSERVICES PROJECT


TEAM MEMBERS:
1. Rahul Jangir — Roll No: 22CSU385
2. Viraj Yadav — Roll No: 22CSU399
3. Tushar — Roll No: 22CSU452


MODULES AND SERVICES


1. PASSENGER SERVICE

Coded by: Rahul Jangir (22CSU385)
Description: Handles all passenger-related operations.

Port: 8081
Base URL: /passengers
Working Endpoints:
- GET    /passengers/all              → Fetch all passengers
- GET    /passengers/all/details      → Fetch passenger details DTO
- GET    /passengers/{id}             → Fetch specific passenger by ID
- POST   /passengers/add              → Add a new passenger
- DELETE /passengers/delete/{id}      → Delete passenger by ID

Example Calls (via API Gateway - port 8080):
GET    http://localhost:8080/passengers/all
POST   http://localhost:8080/passengers/add
DELETE http://localhost:8080/passengers/delete/2



2. FLIGHT SERVICE

Coded by: Viraj Yadav (22CSU399)
Description: Manages flight information and schedules.

Port: 8082
Base URL: /flights
Working Endpoints:
- GET    /flights/all                → Fetch all flights
- GET    /flights/{id}               → Fetch specific flight details
- POST   /flights/add                → Add a new flight
- PUT    /flights/update/{id}        → Update flight details
- DELETE /flights/delete/{id}        → Delete flight by ID

Example Calls (via API Gateway - port 8080):
GET  http://localhost:8080/flights/all
POST http://localhost:8080/flights/add
PUT  http://localhost:8080/flights/update/3



3. BOOKING SERVICE

Coded by: Tushar (22CSU452)
Description: Handles flight bookings for passengers.

Port: 8083
Base URL: /bookings
Working Endpoints:
- GET    /bookings/all                 → Fetch all bookings
- GET    /bookings/{id}                → Get booking by ID
- POST   /bookings/add                 → Create a new booking
- PUT    /bookings/update/{id}         → Update booking details
- DELETE /bookings/delete/{id}         → Delete booking
- GET    /bookings/passenger/{passengerId} → Get bookings by passenger ID
- GET    /bookings/all/details         → Detailed booking info (DTO list)

Example Calls (via API Gateway - port 8080):
GET  http://localhost:8080/bookings/all
POST http://localhost:8080/bookings/add
GET  http://localhost:8080/bookings/passenger/1



4. EUREKA SERVER

Coded by: Rahul Jangir (22CSU385)
Description: Service registry for all microservices.

Port: 8761
URL: http://localhost:8761/



5. CONFIGURATION SERVER

Coded by: Tushar (22CSU452)
Description: Centralized configuration for all services.

Port: 8888
Git Config Repo: https://github.com/TusharJangr/Flightsevice-config
Status: Minor issue detected — currently some services fetch configuration directly from local properties instead of the Git repo.



6. API GATEWAY

Coded by: Viraj Yadav (22CSU399)
Description: Routes and secures all incoming requests to appropriate microservices.

Port: 8080
Routes:
- /flights/**     → flightservice (port 8082)
- /passengers/**  → passengerservice (port 8081)
- /bookings/**    → bookingservice (port 8083)


COMMON CODE

Configuration Server: Tushar (22CSU452)
API Gateway & Security Config: Viraj Yadav (22CSU399)
Eureka Server Setup & Integration: Rahul Jangir (22CSU385)


WORKING STATUS

 Passenger Service — Fully functional
 Flight Service — Fully functional
 Booking Service — Fully functional
 Eureka Server — Working and registers all services
 Configuration Server — Minor issue (services fallback to local config if Git config fails)
 API Gateway — Successfully routing requests to all services

All services communicate properly via Eureka discovery and Feign clients.
Only configuration server has a minor connectivity/config fetch issue.


HOW TO RUN

1. Start MySQL and create database "airline_db".
2. Run services in the following order:
   1) Configuration Server (port 8888)
   2) Eureka Server (port 8761)
   3) Passenger Service (port 8081)
   4) Flight Service (port 8082)
   5) Booking Service (port 8083)
   6) API Gateway (port 8080)

3. Access via browser or Postman:
   Flights:     http://localhost:8080/flights/all
   Passengers:  http://localhost:8080/passengers/all
   Bookings:    http://localhost:8080/bookings/all


DEFAULT CREDENTIALS (LOCAL SETUP)

Username: root
Password: Rahul@1234
Database: airline_db


NOTES

- All endpoints tested through the API Gateway.
- Services register correctly on the Eureka dashboard.
- Config server works partially (services still run properly using local config).
- Credentials and environment variables should be secured before deployment.


END OF README

