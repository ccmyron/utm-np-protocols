# net-prog-protocols
This is the implementation of the 2nd lab given at for Network Programming course at UTM.  
It implements a server and  client using Java sockets. And also offers the possibility to download the logs using HTTP.  

### Prerequisites:
* JDK 11
* Maven

### To launch the project:
1. Clone the code;
2. Open it as a project in Intellij IDEA
3. Launch the Spring Boot Application
4. Launch Client from com.utm.prprotocols.sockets.Client
5. Configure Client to be launched as multiple instances
6. Launch some clients and have fun!

### Small features:
1. To communicate with other clients, prefix your message with ```say <message>```
2. To download the chat logs, navigate to localhost:8080/download
