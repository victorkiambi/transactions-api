package com.switchlink.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long customerId;
    private String username;
    private String customerEmail;
    private Integer customerPhone;


    public JwtResponse(String jwt, Long customerId, String username, String customerEmail, Integer customerPhone) {
    }
}
