# Prewave microservice 
#### Version: 1.0.0 ####

### Summery ###
This is a backend service using Spring Boot with java to manage a tree data
structure. The tree will be represented using a set of edges, where each edge connects two
nodes. 

### Requirements ###
- Java 21 
- Maven
- Docker
- Postgres
- Redis
- Flyway
- JOOQ

### Setup ###
- Close repo locally:   
- Run maven command:    mvn clean package
- Run docker command:   docker-compose down -v
- Run docker command:   docker-compose up -d

### Ports ###
- Postgres: 5432
- Redis:    6379
- service:  8080 (+ 5005 debug mode)

### APIs ###
##### Query tree nodes by nodeId #####
- Summary:   Gets a tree by its 'nodeId'
- Reference: NodeController.getNodesAsTree
```bash 
curl --location 'http://localhost:8080/api/v1/nodes/1/tree?page=0&size=40' \
  --header 'x-api-key: 2d3cb8e0-f64c-4965-9fad-4ecf3477edd2'
```

##### Create edges in the tree #####
- Summary:   Create an edge between two distinct nodes
- Reference: EdgeController.createEdge
```bash
curl --location 'http://localhost:8080/api/v1/edges' \
  --header 'Content-Type: application/json' \
  --header 'x-api-key: 2d3cb8e0-f64c-4965-9fad-4ecf3477edd2' \
  --data '{"fromId": 1, "toId": 2}' 
```

##### Delete edges in the tree #####
- Summary:  Deletes an edge between two distinct nodes
- Reference: EdgeController.deleteEdge
```bash
curl --location --request DELETE 'http://localhost:8080/api/v1/edges?fromId=1&toId=3' \
  --header 'x-api-key: 2d3cb8e0-f64c-4965-9fad-4ecf3477edd2'
```

### Helpers ###
- Redis cli: docker exec -it redis-cache redis-cli
- PgAdmin to connect to postgres db
- Local debug: Create a remote JVM debug configurations
  - debugger mode: attach to remote JVM
  - Transport: socket
  - Host: localhost
  - Port: 5005