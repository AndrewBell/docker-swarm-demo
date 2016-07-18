Build docker images:

`docker-compose build`

Run docker image:

`docker-compose up`

Build a docker swarm:

`docker-compose build`
`docker swarm init`
`docker network create -d overlay mynet`
`docker service create --replicas 3 --name adjective --publish 8010:8010/tcp --network mynet -e SPRING_PROFILES_ACTIVE=prod recursivechaos/adjective`
`docker service create --replicas 3 --name noun --publish 8020:8020/tcp --network mynet -e SPRING_PROFILES_ACTIVE=prod recursivechaos/noun`
`docker service create --replicas 1 --name gateway --publish 8000:8000/tcp --network mynet -e SPRING_PROFILES_ACTIVE=prod recursivechaos/gateway`
`docker service create --replicas 1 --name config --publish 8888:8888/tcp --network mynet -e SPRING_PROFILES_ACTIVE=prod recursivechaos/config`
`curl localhost:8000\adjectives\random`