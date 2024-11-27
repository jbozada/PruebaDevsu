package com.devsu.hackerearth.backend.account.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Account extends Base {
    private String number;
    private String type;
    private double initialAmount;
    private boolean isActive;

    @Column(name = "client_id")
    private Long clientId;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
}
