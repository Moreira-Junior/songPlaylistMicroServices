#Hello! Welcome to the guidelines of the cloud native final challenge 2

- Here is the link of video of me showing a bit of my solution: https://youtu.be/7odmMZg3gA0

## Things you are going to need in order to run the challenge, you can easily click on each one and go to a tutorial on how to install it:

## Enviroment
 - [Java11](https://www.java.com/en/download/help/download_options.html)
 - [Gradle](https://gradle.org/install/)
 - [Tomcat](https://www.digitalocean.com/community/tutorials/install-tomcat-9-ubuntu-1804-pt)
 - [Eureka](https://github.com/Netflix/eureka/wiki/Building-Eureka-Client-and-Server)
 - [Hystrix - Dashboard](https://github.com/Netflix/Hystrix/wiki/Getting-Started)
 - [MongoDB](https://www.mongodb.com/docs/manual/installation/)

## Setting up
 - You have to install tomcat to deploy the .war files of Eureka and Hystrix dashboard.
 - Then you'll have to clone this repo.
 - Next, for each microservice, you'll have to go inside each folder and run the command: ./gradlew build and then look go to build/libs and run the jar (with java -jar {nameOfTheMicroservice}-0.0.1-SNAPSHOT.jar).

## Endpoints
get http://localhost:8088/app/song/ - list all songs
get http://localhost:8088/app/song/id/{id} - get song by id
get http://localhost:8088/app/song/name/{name} - get song by name
get http://localhost:8088/app/playlist/ - get all playlists
get http://localhost:8088/app/playlist/{id} - get playlist by id
post http://localhost:8088/app/song/{name} - post a new song
post http://localhost:8088/app/playlist/{ids} - post ids of songs to create a playlist
delete http://localhost:8088/app/playlist/{id} - delete a playlist by id
delete http://localhost:8088/app/song/id/{id} - delete a song by id
delete http://localhost:8088/app/song/name/{name} - delete a song by name

