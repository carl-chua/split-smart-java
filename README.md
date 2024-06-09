# Spring boot app

Java 21

## Getting Started

### Add the following config

`app.yaml`

```
env_variables:
SUPABASE_URL: ''
SUPABASE_KEY: ''
```

`application.properties`

```
SUPABASE_URL=
SUPABASE_KEY=
```

### Install dependencies

```bash
mvn clean install
```

This command cleans the Maven project by deleting the target directory
This command builds the Maven project and installs the project files (JAR, WAR, pom.xml, etc.) to the local repository

### Run the project

```bash
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) with your browser to see the result.

## Deploy

```bash
mvn package appengine:deploy
```
