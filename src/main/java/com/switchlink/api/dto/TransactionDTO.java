package com.switchlink.api.dto;

import com.switchlink.api.models.TransactionType;
import lombok.Data;

import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID transactionReferenceNo;

    private double transactionAmount;

    private TransactionType transactionType;
}
