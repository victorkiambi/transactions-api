package com.switchlink.api.dto;

import com.switchlink.api.models.Account;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long customerId;

    private String username;

    private String email;

    private Integer phone;
    private List<Account> accounts;
}
