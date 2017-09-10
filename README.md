# spring-batch

spring-batch-0.0.1-SNAPSHOT.jar

Run only specified jobs

Only job1

$ java -jar target/spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=job1
Only job2

$ java -jar target/spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=job2
job1 and job2

$ java -jar target/spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=job1,job2
