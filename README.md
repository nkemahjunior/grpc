
# gRPC with Spring Boot


**README written by ChatGPT**


- check: https://grpc.io/docs/what-is-grpc/introduction/
- check: https://grpc.io/docs/what-is-grpc/core-concepts/
- check: https://grpc.io/docs/languages/java/

- check: https://docs.spring.io/spring-grpc/reference/getting-started.html

#
## Introduction to gRPC - 

gRPC (gRPC Remote Procedure Calls) is a high-performance, language-agnostic framework that enables easy and efficient communication between distributed services. It allows you to define services and message types using Protocol Buffers (Protobuf), and gRPC handles the encoding, communication, and routing for you. This makes it ideal for microservices architecture where services need to interact with each other across different programming languages and platforms.
 **For more details on gRPC, visit the official links above**

### Advantages of gRPC

- **Efficient communication**: gRPC uses Protocol Buffers, a binary format that is both compact and fast. This leads to faster serialization/deserialization and reduced data transfer over the network.
- **Language agnostic**: gRPC supports multiple programming languages, including Java, Go, Python, C++, and many more, allowing easy integration between different services written in different languages.
- **Strong API contract**: With gRPC, services and messages are defined using Protobuf files. This ensures that all services have a clear API contract and ensures compatibility between different services.
- **Support for multiple communication patterns**:
    - **Unary**: Single request, single response.
    - **Server streaming**: Server sends multiple messages to the client.
    - **Client streaming**: Client sends multiple messages to the server.
    - **Bidirectional streaming**: Both client and server send multiple messages to each other.
- **Built-in authentication, load balancing, and bidirectional streaming**: gRPC provides several advanced features like authentication, built-in retries, and more.

### How gRPC Works

gRPC follows a simple client-server model for calling methods on distributed services. The client sends a request to the server, and the server responds back with a result. The communication is based on HTTP/2, which enables multiplexing of requests, flow control, and efficient connection management.

### Key Components of gRPC

1. **Protocol Buffers (Protobuf)**: A language-agnostic data serialization format used by gRPC. It defines the service methods and message types in a `.proto` file.

2. **gRPC Server**: This is where the actual business logic resides. The server is responsible for implementing the service methods and handling the incoming requests from clients.

3. **gRPC Client**: The client is responsible for calling the service methods exposed by the gRPC server. The client can call these methods just like regular local method calls, making it easy to interact with remote services.

4. **Stub**: A client-side proxy that makes remote procedure calls to the server. When you define a service in Protobuf, gRPC generates the corresponding client and server code (including the stub) automatically.

5. **Channel**: The communication medium between the client and the server. It manages the connections and helps establish communication between client and server.

## Getting Started with Spring gRPC

Spring gRPC is a framework that makes it easier to integrate gRPC into Spring Boot applications. It offers auto-configuration, making it easier to set up gRPC clients and servers within a Spring context.

### Prerequisites

1. **Spring Boot**: A popular framework for building Java-based applications.
2. **gRPC**: A high-performance RPC framework.
3. **Protocol Buffers**: A binary serialization format used by gRPC.

### Steps for Setting Up gRPC with Spring Boot

1. **Add Dependencies**: You need to add the `spring-boot-starter-grpc` dependency to your `pom.xml` to enable gRPC support in your Spring Boot application.

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-grpc</artifactId>
   </dependency>

2.  If you are using a servlet server (e.g., **with spring-boot-starter-web**), Spring Initializr will automatically add the required dependency **spring-grpc-server-web-spring-boot-starter**. This is the necessary dependency for enabling gRPC on a servlet-based server.

## Example of a simple .proto file
``` 
syntax = "proto3";

option java_multiple_files = true; //Generates each message type in its own separate .java file.
option java_package = "com.zeco.grpc.serviceB.proto";  // Specifies the Java package for the generated classes.
option java_outer_classname = "HelloWorldProto";   //Specifies the outer class name that wraps all the generated code.

// The greeting service definition.
service Simple {
  // Sends a greeting- A unary RPC that sends a single request (HelloRequest) and receives a single response (HelloReply).
  rpc SayHello (HelloRequest) returns (HelloReply) {
  }
  
  // A server-streaming RPC, where the server sends multiple responses (HelloReply messages) for a single request (HelloRequest).
  rpc StreamHello(HelloRequest) returns (stream HelloReply) {}
}

// The type of Hello request mesage - used above in that service.
message HelloRequest {
  string name = 1;
}

// The type for the response message- used in that service above
message HelloReply {
  string message = 1;
}
```

- the client and server will use this same proto file 
- the grpc compiler will generate the necessary code from that file and also getter and setter methods for those fields. it does this when you run ``` mvn clean package```
- Those field numbers are unique identifiers assigned to each field in a message. They are used during the serialization of data to make sure each field is properly identified when the message is being processed (either when sending or receiving it).
-  field numbers must be unique within the same message but can be reused across different messages. For instance, you could have another message with a field name = 1, but it would refer to a completely different field in that context.