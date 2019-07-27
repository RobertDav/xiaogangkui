mvn clean install -DskipTests
scp -P 58422 target/cantaloupe-1.0-SNAPSHOT.jar root@obas:/usr/local/tomcat/


# 在obas 机器上的/usr/local/tomcat 先kill 掉当前项目的进程 然后执行 ./start.sh