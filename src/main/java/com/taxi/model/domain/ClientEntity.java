package com.taxi.model.domain;

import com.taxi.model.entity.Person;
import com.taxi.model.entity.enums.Role;

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
