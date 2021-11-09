package com.switchlink.api.services;

import com.sun.istack.NotNull;
import com.switchlink.api.models.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;


public class CustomerDetailsImpl implements UserDetails {

    private Long customerId;


    private String username;

    private String password;

    private String customerEmail;

    private Integer customerPhone;

    public CustomerDetailsImpl(Long customerId, String username, String password, String customerEmail, Integer customerPhone) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public static CustomerDetailsImpl build(Customer customer) {
        return new CustomerDetailsImpl(
                customer.getCustomerId(),
                customer.getPassword(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getPhone()
                );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Integer getCustomerPhone() {
        return customerPhone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomerDetailsImpl user = (CustomerDetailsImpl) o;
        return Objects.equals(customerId, user.customerId);
    }

}
