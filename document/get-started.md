# Overview

What's' in the README ?

* How to get the API Doc Online ?
* How to build the project ?
* How to test the project ?
* How to debug the project in IDE ?
* How to build the docker image ?
* How to start the project in the way of docker-compose ?

> Generally, there are 3 ENV need to cover for the project:
>
> * development
> * test
> * production
>
> We use Spring Profile to differentiate the environment , more detail please refer to `Appendix - Spring Profile`

## How to get the API Doc Online ?

Please follow the instructions to start the API doc server.

```sh
cd mes/apidoc
gradle clean bootRun
```

Then open the browser and enter the following URL.

> http://127.0.0.1:8080/swagger-ui.html


## How to build the project ?

```sh
gradle clean build -x test
```

or 

```sh
gradlew clean build -x test
```

> remember pass `-x test` if you only want verify the source code compilation.

## How to test the project ?

The SUNDRA backend depends on outside server before it gets started.

* db
* db_seed


So please remember start the required db before test.

```
 docker-compose -f docker-compose.test.yml up
```

Finally start the test.


```
gradle clean test
```

or 

```
gradlew clean test
```


## How to debug the project in IDE ?

The SUNDRA backend depends on outside server before it gets started.

* db
* db_seed
* ...

So please remember start the required db before debug.

```
docker-compose -f docker-compose.dev.yml up
```


Add new Gradle configuration in IDE , and run it in debug mode.

> The configuration should match the following CMDs.

```
cd mes/server
gradle clean bootRun -PjvmArgs="-Dspring.profiles.active=development"
```

or
 
```
cd mes/server
../../gradlew clean bootRun -PjvmArgs="-Dspring.profiles.active=development"
```

## How to build the docker image ?

We take bitbucket's pipeline to do CI/CD.

The docker image is build automatically in the way of `bitbucket-pipelines.yml` 

```
docker login --username $DOCKER_USERNAME --password $DOCKER_PASSWORD
docker build -t $IMAGE_NAME -t $ALIYUN_IMAGE_NAME .
docker push $IMAGE_NAME
docker login --username $ALIYUN_DOCKER_USERNAME --password $ALIYUN_DOCKER_PASSWORD $ALIYUN_DOCKER_REGISTRY
docker push $ALIYUN_IMAGE_NAME
```

So please feel free to replace the $ENV_VAR and run it on the CMD line.

## How to start the project in the way of docker-compose ?

The mes server is started at port `8081`


```
docker-compose pull
docker-compose build
docker-compose up -d
```


> speed up by `docker-compose.cn.yml` if you are in China.

```
docker-compose -f docker-compose.cn.yml pull
docker-compose -f docker-compose.cn.yml build
docker-compose -f docker-compose.cn.yml up -d
```


# Appendix - Spring Profile

We designed following profiles to match our DevOps requirements.

name | desc
---|---
development | for development in IDE (e.g. debug) , normally for `gradle bootRun`
test | for unit test on local machine , normally for `gradle test`
production ( **enabled** by default) | for production env , normally for `java -jar`

> Refz
> https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html

**How to use?**

To pass the options `-Dspring.profiles.active=<profile-name>` to JVM , for example:

* for Development

```
gradle clean bootRun -PjvmArgs="-Dspring.profiles.active=development"
```


* for Test

```
gradle clean test 
```

or

```
gradle clean test -PjvmArgs="-Dspring.profiles.active=test" 
```

> The `-Dspring.profiles.active=test` is not mandatory, since we have specified the active profile in Test class
>
> ```
>   @ActiveProfiles(profiles = "test")
>   public abstract class AbstractTest { }
> ```


* for Production

```sh
java -jar server-1.0.0-SNAPSHOT.jar 
```

or

```sh
java -jar server-1.0.0-SNAPSHOT.jar -Dspring.profiles.active=production 
```



