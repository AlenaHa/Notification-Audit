# 1. Have your Java app ready to run.
# 2. Create the docker file
```
# Raw image where we only add Java 8
FROM java:8

# Create and use the /tmp folder on the newly created Docker images (which is like an OS)
VOLUME /tmp

# Add the jar that you want to the new Docker image
ADD notification-service-1.0-SNAPSHOT.jar notification-service-1.0-SNAPSHOT.jar

# If it's a web app expose its port
EXPOSE 8777

# When running the Docker image you want to start the jar that you copied
ENTRYPOINT ["java","-jar","notification-service-1.0-SNAPSHOT.jar"]
```
# 3. Add the ```com.spotify.docker-maven-plugin```

```xml
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>${dependency.docker-maven-plugin.version}</version>
    <configuration>
        <imageName>${project.artifactId}</imageName>
        <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```

# 4. Build the docker image using the below command
```sh
mvn docker:build
```

# 5. Run the docker image
```sh
docker run -p 8777:8777 notification-service
```

*PORTS BEFORE THE IMAGE!!!!*

# 6. Use it as you would have started the jar using Intellij / Command line
