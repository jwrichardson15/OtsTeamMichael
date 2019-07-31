package com.credera.parks.security.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class LoginRequest implements Serializable {

    private String username;
    private String password;

}
