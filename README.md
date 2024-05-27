```text
mvn clean compile dependency:copy-dependencies package
```

Use this version on linux
```text
java -cp target/GemFireHello2-1.0.jar:target/dependency/*  com.vmware.gemfire.GemFireHello2
```

Use this version on Windows
```text
java -cp "target/GemFireHello2-1.0.jar;target/dependency/*"  com.vmware.gemfire.GemFireHello2
```

Create a locator and two servers in gfsh
```
start locator --dir state/locator1
start server --server-port 0 --dir state/s1
start server --server-port 0 --dir state/s2
```

Create a region for the data
```
create region --name presidents --type=PARTITION
```

Examples of put and get commands
```
put --region presidents --key 1 --value "George Washington"
put --region presidents --key 2 --value "John Adams"
put --region presidents --key 3 --value "Thomas Jefferson"
get --region presidents --key 2
```
