# Overview



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
* Group are managed in tree structured perspective.
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


## Team Collaboration

### Model

![](document/images/team-model-diagram.png)

### Features
