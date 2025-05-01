package com.zeco.grpc.serviceB.service;

import com.zeco.grpc.serviceB.proto.SimpleGrpc;
import io.grpc.StatusRuntimeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zeco.grpc.serviceB.proto.HelloRequest;
import com.zeco.grpc.serviceB.proto.HelloReply;

import java.util.Iterator;

@Service
public class GrpcClientService {

    private static Log log = LogFactory.getLog(GrpcClientService.class);

    @Autowired
    private SimpleGrpc.SimpleBlockingStub blockingStub;

    public String greet(String name) {
        // Creating a request with the user's name.
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;


        try {
            // Call the original method on the server.
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) { //use spring exception handler next time
            // Log a warning if the RPC fails.
            log.warn( "RPC failed: " + e.getStatus());
            return "RPC failed: " + e.getStatus();

        }

        log.info("Greeting: " + response.getMessage());

        return response.getMessage();

    }



    public String greetStream(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        Iterator<HelloReply> responseStream;

        try {
            responseStream = blockingStub.streamHello(request);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: " + e.getStatus());
            return "RPC failed: " + e.getStatus();
        }

        StringBuilder allMessages = new StringBuilder();
        while (responseStream.hasNext()) {
            HelloReply reply = responseStream.next();
            log.info("Greeting: " + reply.getMessage());
            allMessages.append(reply.getMessage()).append("\n");
        }

        return allMessages.toString();
    }
}
