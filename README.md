# spring-batch

spring-batch-0.0.1-SNAPSHOT.jar

Run only specified jobs

Only job1

$ java -jar target/spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=job1
Only job2

$ java -jar target/spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=job2
job1 and job2

$ java -jar target/spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=job1,job2


3. CommandLineJobRunner example
Usage :

CommandLineJobRunner jobPath <options> jobIdentifier (jobParameters)
To run above spring batch job, type following command :

$ java -cp "target/dependency-jars/*:target/spring-batch-0.0.1-SNAPSHOT.jar" org.springframework.batch.core.launch.support.CommandLineJobRunner spring/batch/jobs/job-read-files.xml readJob
For jobParameters, append to the end of the command :

$ java -cp "target/dependency-jars/*:target/spring-batch-0.0.1-SNAPSHOT.jar" org.springframework.batch.core.launch.support.CommandLineJobRunner spring/batch/jobs/job-read-files.xml readJob file.name=testing.cvs
To run it on a schedule, normally, you can copy above commands into a .sh file, and run it with any scheduler commands, like cron in *nix