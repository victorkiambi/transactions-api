package com.switchlink.api.dto;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class AccountTransactionsDTO {
    private Long accNo;
    private String accName;
    private String accBranch;
    private double minBalance;

    @Transient
    private transient double transactionAmount;
    private transient Long receiverAccNo;

}
