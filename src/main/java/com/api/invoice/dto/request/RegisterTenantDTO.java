package com.api.invoice.dto.request;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterTenantDTO {
    @NotNull(message = "name is required")
    @NotEmpty(message = "name is required")
    private String name;
    @NotNull(message = "country is required")
    @NotEmpty(message = "country is required")
    private String country;
    @Range(min=10, max=25)
    @NotNull(message = "maxEmployees is required")
    private int maxEmployees;
    @Length(min=8, max=20,message = "username must be between 8 and 20")
    @NotNull(message = "username is required")
    @NotEmpty(message = "username is required")
    private String username;
    @NotEmpty(message = "firstName is required")
    @NotNull(message = "firstName is required")
    private String firstName;
    @NotEmpty(message = "lastName is required")
    @NotNull(message = "lastName is required")
    private String lastName;
    @NotEmpty(message = "email is required")
    @NotNull(message = "email is required")
    @Email(message = "email is required")
    private String email;
    @Length(min=8, max=100,message = "password must be between 8 and 100")
    @NotEmpty(message = "password is required")
    @NotNull(message = "password is required")
    private String password;
    @NotEmpty(message = "stripeToken is required")
    private String stripeToken;
    @NotEmpty(message = "plan is required")
    private String plan;
    private String subscriptionId;
    private String customerId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
