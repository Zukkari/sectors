# Sectors

Simple web app using Spring MVC.

Since requirements were not that strict and simple static page was sufficient here, decided to go with simple Spring MVC app.

On the backend used MongoDB instead of traditional relational DB.
Why?
The data inside the selection is structured, so we would not want to lose the structure of the data.
Storage in the relational database would have been more difficult for this task.

Base of the database case be found in `db_dump.tar.gz` (Dumped using `mongodump`).

## Running

App can be started by executing following steps:

1. Build the app using Maven

```shell
mvn package
```

2. Start up the docker-compose:

```shell
docker-compose up -d
```

This will create the necessary containers, and the application will be running on `http://localhost:8080`

