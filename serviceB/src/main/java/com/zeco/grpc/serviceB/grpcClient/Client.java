package com.zeco.grpc.serviceB.grpcClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;
import com.zeco.grpc.serviceB.proto.SimpleGrpc;

@Configuration
public class Client {

    @Bean
    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
        return SimpleGrpc.newBlockingStub(channels.createChannel("serviceA"));// check your application.properties to see the server address which 'serviceA' refers to
    }

    @Bean
    SimpleGrpc.SimpleStub asyncStub(GrpcChannelFactory channels) {
        return SimpleGrpc.newStub(channels.createChannel("serviceA"));
    }
}
