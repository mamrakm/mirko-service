package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    private Long id;

    /**
     * Column names are defined in accordance with migration.sql.
     */
    @Column(name = "name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "subject")
    private List<BankAccount> accounts;
}
