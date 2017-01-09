# Required software:
This project uses Maven as build system.

# Prerequisites:
Git
JDK 8
Be sure that your JAVA_HOME environment variable points to the jdk1.8.0 folder extracted from the JDK download.

# Building from source:
Run:
==========

    mvn clean package

or

==========
    mvn clean package -DskipTests

War file will be under <project_dir>/target

# Docker

Run app using docker compose:
docker-compose up

This command allows to build project without installing anything on your PC:
docker run -it --rm --name my-maven-project -v $PWD:/usr/src/mymaven -w /usr/src/mymaven maven:3.3.9-jdk-8 mvn clean install
