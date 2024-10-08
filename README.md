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
java -cp target/gemfire-hello3-1.0-SNAPSHOT.jar:target/dependency/*  com.vmware.gemfire.GemFireHello3 load
```
Deploy package to the server inside gfsh
```
deploy --jar=target/gemfire-hello3-1.0-SNAPSHOT.jar
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
# Test Gemfire Text Search
Now lets create a text index on the presidents
```
create lucene index --name=presidentIndex --region=/presidents --field=name
```

test lucene index
```
search lucene --name=presidentIndex --region=/presidents --queryString="Lincoln" --defaultField=name
```

Another search
```
search lucene --name=presidentIndex --region=/presidents --queryString="John" --defaultField=name
```

Use boolean AND
```
search lucene --name=presidentIndex --region=/presidents --queryString="John AND Adams" --defaultField=name
```

Use boolean OR
```
search lucene --name=presidentIndex --region=/presidents --queryString="John OR Bill" --defaultField=name
```

Use boolean NOT
```
search lucene --name=presidentIndex --region=/presidents --queryString="George AND NOT Bush" --defaultField=name
```

Grouping
```
search lucene --name=presidentIndex --region=/presidents --queryString="(George AND Bush) OR (James AND NOT Madison)" --defaultField=name
```

Use Fuzzy Match
```
search lucene --name=presidentIndex --region=/presidents --queryString="Eisinhower" --defaultField=name
search lucene --name=presidentIndex --region=/presidents --queryString="Eisinhower~" --defaultField=name
```

Use boosting
```
search lucene --name=presidentIndex --region=/presidents --queryString="George^2 Adams" --defaultField=name
```
The ^2 indicates that you want to boost the relevance score of the term "George" by a factor of 2. 

This means that results containing "George" will be ranked higher in the search results compared to those containing "Adams".

The term "Adams" is included in the query without any boost, so it will have a standard relevance score.


Now you can use the CLI to do the search as well:
```
java -cp target/gemfire-hello3-1.0-SNAPSHOT.jar:target/dependency/*  com.vmware.gemfire.GemFireHello3 search "George AND NOT Bush"
```



