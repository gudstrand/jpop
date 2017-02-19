# jpop
## Overview
jreport is a tomcat based REST service that will open a url jsonl resource 
and generate a population report from the contents.  Once the application 
is started the service can be accessed via a web browser, curl, wget, etc..  
 
## Build instructions
To build the executable jar using gradle

	gradle build
	
## To Run
jpop is a java executable and requires java 1.8+.  To run;
	
	java -jar jpop-rest-service-0.1.0.jar 
	
## Using JPOP
The Jpop REST service accepts only one parameter, that is the url of the 
jsonl data source.  If none is provided it will default to 
https://dl.dropboxusercontent.com/u/2436323/cities.jsonl
 
 To access another url provide the parameters on the request line :
 
 http://localhiost:/8080/population?url="https://dl.dropboxusercontent.com/u/2436323/cities.jsonl"
	
	