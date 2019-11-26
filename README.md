# Phonebook
The project is task for testing. 
Develop an HTTP based API for a Phonebook. API should use JSON. All JVM based languages and frameworks are allowed.

Only fixed requirement: It should allow me as a user to upload my old phonebook database which is in XML format.
Author: W.Y. Wei

Compiler environment：Eclipse.2019-06


***System requirement:*** 

    import 10 refernence library: 
      apache-commons.jar
      commons-fileupload-1.4.jar
      commons-io-2.5.jar
      i-spring-1.0.0.jar
      javax.servlet.jar
      json-20180813.jar
      org.springframework.context-3.0.4.RELEASE.jar
      org.springframework.web-3.0.6.release.jar
      spring-core-5.2.0.RELEASE.jar
      spring-jcl-5.2.0.RELEASE.jar

***Document description：***

     API.java:http based API for phonebook
     test.java:API test.
     Server.java:Create a server with the ability to store posts.
     ServerThread.java:Thread management on the server side by using thread. Separate it from the client thread.
     Client.java: Send and request data as a client like a server.
     upload.java：Upload the XML file and parse it into json data.
     

       
***API***
**startServer**

Parameter: serverPort(int)

Return value: void

Function description: It creates a new process and starts the server.

Instruction: Parameter is the server port number.

Code case: 
    
    'API.startServer(8000);'

**Client**

Parameter:filepath(string), url(string),clientPort(int)

Return value: page(string)--HTML page code returned from the server

Precautions：filepath is the full path of the XML file, and url is the full URL without a port number. clientPort should be the same as serverPort.

Code case:

    'string page= API.Client('/Users/phonebook.xml','http://localhost',8000)'

***Test considerations***

The API has been integrated into the gaile2.jar file. The file location is in the root directory, which should be used in the code

     'import server.API'
 

Import gaile2.jar in the test case, also you need to import the jar package under the "jar.resourse" folder to use it.


Please use startServer to start the server, and then use the client for XML file upload.






    
