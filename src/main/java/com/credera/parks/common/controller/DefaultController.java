package com.credera.parks.common.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("")
public class DefaultController {

    @GetMapping("/heartbeat")
    @ApiOperation(value = "Heartbeat test", nickname = "getHeartbeat", notes = "Heartbeat check", authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<String> heartbeat(HttpServletRequest request) {
        return ResponseEntity.ok("healthy");
    }
}
