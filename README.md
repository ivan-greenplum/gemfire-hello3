Setup statistics so we can monitor stats at the member level
```
mkdir state/locator1
mkdir state/s1
mkdir state/s2
cp gemfire.properties state/locator1
cp gemfire.properties state/s1
cp gemfire.properties state/s2
```

Create a locator
```
start locator --dir state/locator1 --properties-file state/locator1/gemfire.properties
```
Start s1 member
```
start server --server-port 0 --dir state/s1 --properties-file state/s1/gemfire.properties
```

Start s2 member
```
start server --server-port 0 --dir state/s2 --properties-file state/s2/gemfire.properties
```

Create a region for the data
```
create region --name presidents --type=PARTITION
```

Compile source code
```
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
