package com.zeco.grpc.serviceA.exception;

import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class GrpExceptionHandler {


//    @Bean
//    public GrpcExceptionHandler globalGrpcError() {
//
//        return (exception) -> {
//            if (exception instanceof IllegalArgumentException) {
//                return Status.INVALID_ARGUMENT
//                        .withDescription(exception.getMessage() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
//                        .withCause(exception)
//                        .asException();
//            } else if (exception instanceof NullPointerException) {
//                return Status.INTERNAL
//                        .withDescription("Internal server error: Null pointer exception")
//                        .withCause(exception)
//                        .asException();
//            } else {
//                // For all other exceptions, return INTERNAL status
//                return Status.UNKNOWN
//                        .withDescription("Unexpected server error: " + exception.getMessage())
//                        .withCause(exception)
//                        .asException();
//            }
//        };
//
//    }

}
