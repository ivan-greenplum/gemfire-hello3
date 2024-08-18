Start by completing gemfire-hello
https://github.com/ivan-greenplum/gemfire-hello

Connect to gfsh and run a query:
```
query --query="SELECT * FROM /presidents"
```

Compile the code in this repo
```
mvn clean compile dependency:copy-dependencies package
```

Run the code
```
 java -cp target/gemfire-hello3-1.0-SNAPSHOT.jar:target/dependency/*  com.vmware.gemfire.GemFireHello3
```

Run the query again
```
query --query="SELECT * FROM /presidents"
```

# Exercise 1:
Add a command line option to differentiate the actions
```
-- delete-all
-- put-all
-- get-all
-- get-one [key]
-- delete-one [key]
```   

