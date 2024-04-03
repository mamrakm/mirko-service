package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String prefix;
    private String suffix;

    private boolean applyForLoan;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject")
    private Subject subject;
}
