package com.taxi.model.entity;

import com.taxi.model.domain.Person;
import com.taxi.model.domain.enums.Role;

public class ClientEntity extends Person {
    private String email;

    public ClientEntity() {
        this.role = Role.CLIENT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
