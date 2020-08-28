package com.example.demo.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
public class Account {
    @Id
    private String sid;
    private String pwd;
}
