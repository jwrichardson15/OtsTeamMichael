package com.credera.parks.security.controller;

import com.credera.parks.security.model.LoginRequest;
import com.credera.parks.security.model.TokenResponse;
import com.credera.parks.security.service.ParksUserDetailsService;
import com.credera.parks.security.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.credera.parks.common.model.Employee;
import com.credera.parks.common.dto.EmployeeDTO;
import com.credera.parks.common.service.EmployeeService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ParksUserDetailsService userDetailsService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Employee Login", nickname = "login", notes = "returns a JWT authentication token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EmployeeDTO.class)
    })
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

        // Create token
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);

        // Set token as header
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);

        // Get employee that matches the login username
        Employee employee = employeeService.getByUsername(loginRequest.getUsername());

        return ResponseEntity.ok().headers(responseHeaders).body(new EmployeeDTO(employee));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
