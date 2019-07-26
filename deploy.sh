mvn clean install -DskipTests
scp -P 58422 target/cantaloupe-1.0-SNAPSHOT.jar root@obas:/usr/local/tomcat/