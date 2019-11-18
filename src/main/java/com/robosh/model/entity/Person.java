package com.robosh.model.entity;

import com.robosh.model.entity.enums.Role;

import java.util.Objects;

import java.util.Objects;

public class Person {
    protected final Integer personId;
    protected final String name;
    protected final String surname;
    protected final String password;
    protected final String phoneNumber;
    protected  Role role;

    public Person(PersonBuilder<? extends PersonBuilder> personPersonBuilder) {
        this.personId = personPersonBuilder.personId;
        this.name = personPersonBuilder.name;
        this.surname = personPersonBuilder.surname;
        this.password = personPersonBuilder.password;
        this.phoneNumber = personPersonBuilder.phoneNumber;
        this.role = personPersonBuilder.role;
    }

    public int getPersonId() {
        return personId;
    }

//    public void setPersonId(Integer personId) {
//        this.personId = personId;
//    }

    public Role getRole() {
        return role;
    }

//    public void setRole(Role role) {
//        this.role = role;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getSurname() {
        return surname;
    }

//    public void setSurname(String surname) {
//        this.surname = surname;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
    public static class PersonBuilder<SELF extends PersonBuilder<SELF>> {
        protected Integer personId;
        protected String name;
        protected String surname;
        protected String password;
        protected String phoneNumber;
        protected Role role;

        protected PersonBuilder() {
        }

        @SuppressWarnings("unchecked")
        public SELF self() {
            return (SELF) this;
        }

        public Person build() {
            return new Person(self());
        }

        public SELF withPersonId(Integer personId) {
            this.personId = personId;
            return self();
        }

        public SELF withName(String name) {
            this.name = name;
            return self();
        }

        public SELF withSurname(String surname) {
            this.surname = surname;
            return self();
        }

        public SELF withPassword(String password) {
            this.password = password;
            return self();
        }

        public SELF withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return self();
        }

        public SELF withRole(Role role) {
            this.role = role;
            return self();
        }
    }

}
