# Introduction

`TODO`

# Get Started

## Start up

Start an all-in-one sandbox by docker compose

```
docker-compose up -d
```

Check status

```
docker-compose ps
```

Now the REST APIs are all available at the port 8761 (exposed by gateway)

## Customized ENVs

key  | value | desc
---|---|---
TODO | TODO | TODO

## What's in sandbox

The services are available in sandbox.

* zuul
* eureka
* zookeeper
* kafka
* elasticsearch
* akka
* mongodb
* redis

## Scale out

The cluster is supported and it's easy to scale the API server out

```
docker-compose scale api=2
```