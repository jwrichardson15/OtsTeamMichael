package com.credera.parks.common.controller;

import com.credera.parks.common.service.DefaultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class DefaultController {

    @Autowired
    DefaultService defaultService;

    @GetMapping("/heartbeat")
    @ApiOperation(value = "Heartbeat test", nickname = "getHeartbeat", notes = "Heartbeat check", authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<String> heartbeat(HttpServletRequest request) {
        return ResponseEntity.ok("healthy");
    }


    @GetMapping("/categories")
    @ApiOperation(value = "Get Categories", nickname = "getCategories", notes = "returns categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})

    public ResponseEntity getCategories() {
        return new ResponseEntity(defaultService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/parks")
    @ApiOperation(value = "Get Parks", nickname = "getParks", notes = "returns parks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity getAllParks(){
        return new ResponseEntity(defaultService.getAllParks(), HttpStatus.OK);
    }

    @GetMapping("/statuses")
    @ApiOperation(value = "Get Statuses", nickname = "getStatuses", notes = "return statuses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity getAllStatuses(){
        return new ResponseEntity(defaultService.getAllStatuses(), HttpStatus.OK);
    }
}
