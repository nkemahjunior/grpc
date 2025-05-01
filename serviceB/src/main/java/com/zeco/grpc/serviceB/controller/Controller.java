package com.zeco.grpc.serviceB.controller;


import com.zeco.grpc.serviceB.service.GrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testGrpc")
public class Controller {

    @Autowired
    private GrpcClientService grpcClientService;

    @GetMapping("/hello") //http://localhost:8081/testGrpc/hello?name=piqueSandro
    public ResponseEntity<String> testEndpoint(@RequestParam("name") String name){
        return ResponseEntity.ok( grpcClientService.greet( name));
    }

    @GetMapping("/hello/stream")
    public ResponseEntity<String> testEndpoint2(@RequestParam("name") String name){
        return ResponseEntity.ok( grpcClientService.greetStream( name));
    }
}
