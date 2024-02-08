# CSYE 6225: : Network Structure & Cloud Computing

## WebApp

A spring boot project to cover CI/CD.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
4. [Getting Started](#getting-started)
5. [Usage](#usage)
6. [Deployment](#deployment)
7. [Built With](#built-with)


## Introduction

This is a simple Spring Boot project to develop CI/CD pipeline for CSYE 6225:: Network Structure & Cloud Computing.

## Features

- Following are the Endpoints
    - Authenticated
        - /v1/user/self (*GET* & *POST*) - To get user and update user details
    - Public
        - /healthz - To check DB connection status
        - /v1/user(*Post*) - To create a new User.

## Prerequisites (Install Java and Maven)

### For linux 
``` shell
sudo apt-get install openjdk-18-jdk 
 ```
 ### For Mac OS

```
brew install java
```    
### For Windows
- Choose the latest JDK version
- Click the Windows tab
- Select the x64 Installer download link
- Double-click the Java install file in your Downloads folder
- Run the Java installer
- Validate the JAVA_HOME setting
- Confirm the Java PATH variable is set correctly

### Installing Maven 

- Kindly refer this site for Maven installation [Maven](https://maven.apache.org/install.html)

### Postgre SQL Installation
- Kindly refer this site for Postgres installation [Postgres](https://www.postgresql.org/download/)

## Getting Started

- Clone the repository using the following command
```
git clone https://github.com/Joseph-Alex-Chakola/webapp.git
```


## Usage

- Start a postgres DB and set the env variable for the application
``` shell 
export DB_PORT=5432 #Your DB port.
export DB_HOST=localhost # Your host
export DB_NAME=name # Database name
export DB_PASSWORD=password # Database password
export DB_USERNAME=user # Database username
```
- Run test
```
mvn test
```
- After cloning open a terminal at folder webapp and run 

```
mvn spring-boot:run
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java](https://openjdk.org/)
- [Postgres SQL](https://www.postgresql.org/)

