Start by completing gemfire-hello and make sure you have a gemfire installed:
https://github.com/ivan-greenplum/gemfire-hello

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
export GEMFIRE_EXTENSIONS_REPOSITORY_PATH=$GEMFIRE_HOME/extensions
```   

Now lets create a text index on the presidents
```
create lucene index --name=presidentIndex --region=/presidents --field=name
```

test lucene index
```
search lucene --name=presidentIndex --region=/presidents --queryString="*Lincoln*" --defaultField=name
```
