package com.switchlink.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.switchlink.api.models.Customer;
import lombok.Data;

@Data
public class AccountDTO {
    private Long accNo;

    private double minBalance;

    @JsonIgnore
    private Customer customer;
}
