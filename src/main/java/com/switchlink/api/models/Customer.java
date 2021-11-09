package com.switchlink.api.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    private String username;

    @NotNull
    @Size(max = 120)
    private String password;

    @NotNull
    private String email;

    @NotNull
    private Integer phone;

    @NotNull
    @Transient
    private Double minDeposit;

    @OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Account> accounts = new ArrayList<>();

    public Customer(String username, String customerEmail, Integer customerPhone, String encode) {
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
