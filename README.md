# Project: SmartImageService

## Run PostgreSQL with Docker

To start a PostgreSQL container for the `smartimages` database, run the following command:

```bash
docker run -d \
  --name smartimages-postgres \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=smartimages \
  -v smartimages_pgdata:/var/lib/postgresql/data \
  -p 5432:5432 \
  postgres:15
```

```
CREATE TABLE images (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    file_data  BYTEA NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    status     VARCHAR(255) NOT NULL
);
```

## How to Start the Project

### 1. Build the project
```bash
  mvn clean install
```  

### 2. Run in development mode
```bash
  mvn quarkus:dev
```  
