package com.switchlink.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name="account_generator", sequenceName = "account_seq", initialValue = 1000, allocationSize=20)

    @NotNull
    private Long accNo;

    private TransactionType transactionType;

    @NotNull
    private double minBalance;


    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Customer customer;

    @Override
    public String toString() {
        return "Account{" +
                "accNo=" + accNo +
                ", transactionType=" + transactionType +
                ", minBalance=" + minBalance +
                ", customer=" + customer +
                '}';
    }

    //    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private List<Transaction> transactions = new ArrayList<>();



//    public void addTransaction(Transaction transaction){
//        transactions.add(transaction);
//        transaction.setAccount(this);
//    }
//
//    public void removeTransaction(Transaction transaction){
//        transactions.remove(transaction);
//        transaction.setAccount(null);
//    }

}
