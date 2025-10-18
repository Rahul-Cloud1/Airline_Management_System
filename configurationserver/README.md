# Configuration Server

This module is a Spring Cloud Config Server that serves configuration files from a remote Git repository.

## Required environment variables (recommended)

- `CONFIG_SERVER_ADMIN_PASSWORD` - admin password for the simple in-memory user created by Spring Security (used for quick local testing). Default in properties is `change_me` if not set — change it before use.
- `GIT_USERNAME` - (optional) Git username if the config repo is private.
- `GIT_TOKEN` - (optional) Git token/password if the config repo is private.

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
