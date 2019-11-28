package com.taxi.model.entity;

import com.taxi.model.entity.enums.Role;

public class Client extends Person {
    private String email;

    public Client() {
        this.role = Role.CLIENT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
