# jpop
jpop is a tomcat based REST service that will open a jsonl url resource 
and generate a population report from the contents.  Once the application 
is started the service can be accessed via a web browser, curl, wget, etc..
  
## jsonl format
Each row contains a json object containing the following fields:

	city (String): The name of the city
	state (String): The 2 digit state abbreviation
	population (Integer): The population of the city

Example:

	{"city": "New York", "state": "NY", "population": 8550405}
	{"city": "Los Angeles", "state": "CA", "population": 3971883}
 
## Build instructions
jpop is housed in a GitHub repository.  To clone the application create 
a directory of your choosing and run the following command (**you must
have git installed**):

	 git clone git@github.com:gudstrand/jpop.git

Next, to build and launch the webserver cd into the jpop directory and run
the following gradle command:

	gradle bootRun

This command will build and package the server, and launch it on port 8080
on the localhost.

## Using JPOP
The Jpop REST service accepts only one parameter, that is the url of the 
jsonl data source.  If none is provided it will default to 
https://dl.dropboxusercontent.com/u/2436323/cities.jsonl
 
 To access another url provide the parameters on the request line :
 
    http://localhost:8080/population?url=https://dl.dropboxusercontent.com/u/2436323/cities.jsonl

## Compressed resources

jpop can also access gzip compressed jsonl resources too if
they have a .gz extension.  
 
    http://localhost:8080/population?url=https://dl.dropboxusercontent.com/u/7002771/allrecs.jsonl.gz


## Web Report

You can also view an online population report here :

	http://localhost:8080/popreport?url=https://dl.dropboxusercontent.com/u/7002771/allrecs.jsonl.gz