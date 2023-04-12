# Doggy-Revive for Paper 1.19.X

This plugin allows Dogs to respawn at a player's bed incase of death
requires you to have a bed otherwise you could lose your dog

## Prerequisites

In order to try this out you need the following software to be installed on your machine:

* Java version 17 or above (e.g. [OpenJDK](https://openjdk.java.net/install/))
* [Paper](https://papermc.io/downloads)

## Quickstart

Clone the template project to your system:
````bash
git clone https://github.com/Ragnaorok/Doggy-Revive.git
````

This project uses [Maven](https://maven.apache.org/) for building. So on your command line run

````bash
mvn package
```` 

To install your plugin in another Minecraft server just copy the file `target/mc-plugin-template-1.0-SNAPSHOT.jar` to
that server's `plugin` folder. 

```
example output in console if plugin was properly added into server
[19:33:52 INFO]: [Doggy-Revive] Enabling Doggy-Revive v1.0
``` 

Start the Mincraft client on your computer and connect to the local Mincraft server by specifying `localhost` as Server Address.
Alternatively connect to the server using your public ipv4 if you have port forwarded the server


To play with the code e.g. import this plugin in [Intellij](https://www.jetbrains.com/de-de/idea/download/). The
community edition is absolutely sufficient. Read [here](https://www.jetbrains.com/help/idea/maven-support.html) how to
import an existing Maven project.



## Instructions

