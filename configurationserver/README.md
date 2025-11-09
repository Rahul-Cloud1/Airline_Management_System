# Configuration Server

This module is a Spring Cloud Config Server that serves configuration files from a remote Git repository.

## Required environment variables (recommended)

- `CONFIG_SERVER_ADMIN_PASSWORD` - admin password for the simple in-memory user created by Spring Security (used for quick local testing). Default in properties is `change_me` if not set — change it before use.
- `GIT_USERNAME` - (optional) Git username if the config repo is private.
- `GIT_TOKEN` - (optional) Git token/password if the config repo is private.

### New / recommended environment variables

- `SECURITY_USER_NAME` / `SECURITY_USER_PASSWORD` - default in-service user used by the microservices (defaults to `root` / `Rahul@1234` in the example). Services read these via their `application.properties` and can be overridden per environment.
- `SPRING_DATASOURCE_USERNAME` / `SPRING_DATASOURCE_PASSWORD` - global datasource credentials used by services (defaults in example to `root` / `Rahul@1234`).
- `CONFIG_SERVER_ADMIN_USERNAME` / `CONFIG_SERVER_ADMIN_PASSWORD` - admin user for the config server (defaults to `root` / `Rahul@1234` in the example).
- `CONFIG_SERVER_CLIENT_USERNAME` / `CONFIG_SERVER_CLIENT_PASSWORD` - credentials used by clients (e.g. API gateway) when fetching config from the config server (defaults in example to `root` / `Rahul@1234`).

See the project `.env.example` in the repository root for an example list of variables you can set locally. Copy it to `.env` (or set environment variables directly) and do not commit real secrets.

## Run locally (PowerShell)

```powershell
$env:CONFIG_SERVER_ADMIN_PASSWORD = 'your-admin-password'
# If repo private:
$env:GIT_USERNAME = 'your-git-username'
$env:GIT_TOKEN = 'your-git-token'

mvn -f .\pom.xml spring-boot:run
```

## Quick test (once server is running on port 8888)

```powershell
# List available application config (example):
Invoke-RestMethod -Uri http://localhost:8888/flightservice/default
# or using curl
curl http://localhost:8888/flightservice/default
```

## Notes

- The server is configured to use the public Git repo `https://github.com/TusharJangr/Flightsevice-config` by default. Change `application.properties` if you use a different repo.
- For production, do not use the built-in simple user. Integrate a proper auth mechanism and secure your Git tokens.
