package com.mytaxi.model.entity;

import com.mytaxi.model.entity.enums.Role;

public class Client extends Person {
    private String email;

    public String getEmail() {
        return email;
    }

    //  public void setEmail(String email) {
    //     this.email = email;
    //  }

    protected Client(ClientBuilder personPersonBuilder) {
        super(personPersonBuilder);
        this.role = Role.CLIENT;
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public static class ClientBuilder extends PersonBuilder<ClientBuilder> {
        private String email;

        public ClientBuilder() {
        }

        @Override
        public ClientBuilder self() {
            return this;
        }

        public Client build() {
            return new Client(self());
        }

        public ClientBuilder withEmail(String email) {
            this.email = email;
            return self();
        }


    }

}