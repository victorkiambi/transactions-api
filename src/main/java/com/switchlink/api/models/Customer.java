package com.switchlink.api.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long customerId;

    @NotNull
    private String customerName;

    @NotNull
    private String customerEmail;

    @NotNull
    private Integer customerPhone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE)
    private List<Account> accounts = new ArrayList<>();

    public Customer(Long customerId, String customerName, String customerEmail, Integer customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public Customer(long l) {
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account){
        accounts.remove(account);
        account.setCustomer(null);
    }
}
