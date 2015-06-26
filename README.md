[![Build Status](https://travis-ci.org/kennycyb/java-ui-factory-ext.svg)](https://travis-ci.org/kennycyb/java-ui-factory-ext)

# java-ui-factory-ext
Extended component / features to [java-ui-factory](https://github.com/kennycyb/java-ui-factory)

# maven
java-ui-factory-ext is built with maven.  To add java-ui-factory-ext, add this to pom.xml
```
<dependencies>
	<dependency>
		<groupId>com.github.kennycyb</groupId>
		<artifactId>java-ui-factory-core</artifactId>
		<version>0.4-SNAPSHOT</version>
	</dependency>
</dependencies>
```

## sonatype
java-ui-factory-ext is now available in sonatype (since 26 Jun 2015).  To use the snapshot version, add this to pom.xml or settings.xml

```
<repositories>
	<repository>
		<id>snapshots-repo</id>
		<url>https://oss.sonatype.org/content/repositories/snapshots</url>
		<releases>
			<enabled>false</enabled>
		</releases>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>
</repositories>
```
