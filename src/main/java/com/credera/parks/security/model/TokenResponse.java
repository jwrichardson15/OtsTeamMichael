package com.credera.parks.security.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor
public class TokenResponse implements Serializable {

    private String token;
    
}
