# Phonebook
The project is task for testing. 
Develop an HTTP based API for a Phonebook. API should use JSON. All JVM based languages and frameworks are allowed.

Only fixed requirement: It should allow me as a user to upload my old phonebook database which is in XML format.
Author: W.Y. Wei


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

     index.java: Program main function.
     Server.java:Create a server with the ability to store posts.
     ServerThread.java:Thread management on the server side by using thread. Separate it from the client thread.
     Client.java: Send and request data as a client like a server.
     upload.java：Upload the XML file and parse it into json data.
     
***Program running process:***

    1. Start index.java(command line start up is not the same.)
       1.1 The server service has started, port number is 8000. Each time we run the program, the port should be changed to another one manually. for example: 8080 or 80.
       1.2 Users enter the full XML file path. For example:"/Users/angelawei/phonebook.xml".
    2. Users enters the full XML path.
       2.1 Parsing XML files into JSON format.
       2.2 Printing the message header.
       2.3 Printing HTM web page data received from the server.
       2.4 In the server root directory, create a new tmp folder. The JSON data is stored in the "./tmp/phonebook.json" file.
       
       
