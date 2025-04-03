package com.fatihkarakus.user_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "permissions")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Permission() {

    }

    public Permission(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles;

}
