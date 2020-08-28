package com.example.demo.auth.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {
    @Id
    String sid;

    @Column
    String name;
}
