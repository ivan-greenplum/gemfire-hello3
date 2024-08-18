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

# Install Gemfire Text Search
Download this file: vmware-gemfire-search-1.1.1.gfm

And place this file in the directory $GEMFIRE_HOME/extensions

Update .bashrc to add this environment setting and then logout and backin
```
export export GEMFIRE_EXTENSIONS_REPOSITORY_PATH=$GEMFIRE_HOME/extensions
```   

