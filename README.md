# Overview


# Get Started


## Dev Mode

1. Build - Gradle

```shell
gradle clean build
```


2. Start 

By Gradle

```shell
cd cloud/openapi
gradle clean bootRun
```

or 

By Docker-Compose 


```
docker-compose -f docker-compose-singlton.yml build
docker-compose -f docker-compose-singlton.yml up -d
```


## Cloud Mode


Build - Gradle

```shell
gradle clean build
```


```
docker-compose -f docker-compose-cloud.yml build
docker-compose -f docker-compose-cloud.yml up -d
```


# Design


## Architecture

### Functional 


### Technique 



## Identity Management

### Model

![](document/images/identity-model-diagram.png)

### Feature

Basic Features:

* User Management
    * CRUD
    * Password Management
    * bind User & Groups
    * unbind User & Groups
    * bind User & Roles
    * unbind User & Groups
* Role Management
    * CRUD
    * bind Role & Users
    * unbind Role & Users
* Group Management
    * CRUD
    * Parent & Children relationship
    * bind Group & Users
    * unbind Group & Users

Advanced Features:

* Sys Role & App Role are managed in different perspective.
* Tree-structured Group 
* Contact are shown in different perspective
    * Group-Oriented navigator
    * List-Oriented navigator
* Show relationship everywhere
    * From user view, the binding group & role are shown.
    * From group view, the binding users are shown.
    * From role view, the binding users are shown.

## Role Based Access Control

### Model

![](document/images/rbac-model-diagram.png)

### Features

* Tree-structured Resource
* File-Configured Resource
* Annotation-Programming Resource
* Spring Security Integration Supported
* Permission is granted to Role not User
* Show Permission in two way
    * From resource view, show binding roles
    * From role view, show binding resources
    * From user view, show granted permissions

## Team Collaboration

### Model

![](document/images/team-model-diagram.png)

### Features

* Supported Activity Status
    * Draft
    * In Progress
    * Revoked
    * Terminated
* Supported Task Status
    * Pending
    * Processed
    * End
    * Terminated
* Supported Action
    * Start
    * Revoke
    * Reply
    * Forward
    * Done
    * Terminate


# API Doc

## How to start API doc server

```
cd cloud/apidoc
gradle clean bootRun
```

## Where to get the API doc

> http://127.0.0.1:8080/swagger-ui.html