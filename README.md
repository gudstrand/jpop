# jpop
jpop is a tomcat based REST service that will open a jsonl url resource 
and generate a population report from the contents.  Once the application 
is started the service can be accessed via a web browser, curl, wget, etc..
  
## Report contents
Data from the jsonl file will be summarized into a report with the following
fields
	
	totalPopulation - The sum of the populations of all citites
	largestPopulation - The largest population of any city
	mostPopulousCities - City with largest population.  If there are ties all cities are displayed
	states - All the distinct states.
## Report options
The report accepts two parameters
	
	| option       | use                            | default value                                            |
    |--------------|--------------------------------|----------------------------------------------------------|
    | url          | url of jsonl datasource        | https://dl.dropboxusercontent.com/u/2436323/cities.jsonl |
    | ignoreErrors | ignore parsing errors of jsonl | false                                                    |
    
## jsonl format
Each row contains a json object containing the following fields:

	city (String): The name of the city
	state (String): The 2 digit state abbreviation
	population (Integer): The population of the city

Example:

	{"city": "New York", "state": "NY", "population": 8550405}
	{"city": "Los Angeles", "state": "CA", "population": 3971883}
 
## Build instructions
### Git clone
jpop is housed in a GitHub repository.  To clone the application create 
a directory of your choosing and run the following command (**you must
have git installed**):

	 git clone git@github.com:gudstrand/jpop.git

### Gradle build
To build and launch the webserver cd into the jpop directory and run
the following gradle command (mak sure to include the "w", it is not 
a typo!):

	gradlew bootRun

This command will build and package the server, and launch it on port 8080
on the localhost.

#### Alternative
You can also build the application with the command

	gradlew build
	
This command will build the application jar.  That can be run with the 
following commmand

	java -jar jpop-rest-service-0.1.0.jar

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
	
## POST via curl

JPOP also accepts POST requests.  Below are some examples using curl

### Specify URL

  curl --data "url=https://dl.dropboxusercontent.com/u/7002771/statecodetolong.jsonl" http://localhost:8080/population

### ignoreErrors

  curl --data "url=https://dl.dropboxusercontent.com/u/7002771/statecodetolong.jsonl&ignoreErrors=true" http://localhost:8080/population