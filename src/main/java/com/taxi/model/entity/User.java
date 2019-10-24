package com.taxi.model.entity;

import java.util.Objects;

public class User {
    private final Long personId;
    private final String name;
    private final String surname;
    private final String password;
    private final String phoneNumber;
    private Role role;

    private User(Builder builder) {
        this.personId = builder.personId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public static final class Builder {
        private Long personId;
        private String name;
        private String surname;
        private String password;
        private String phoneNumber;
        private Role role;

        private Builder() {
        }

        public Builder withPersonId(Long personId) {
            this.personId = personId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Builder builder = (Builder) o;
            return personId == builder.personId &&
                    Objects.equals(name, builder.name) &&
                    Objects.equals(surname, builder.surname) &&
                    Objects.equals(password, builder.password) &&
                    Objects.equals(phoneNumber, builder.phoneNumber) &&
                    role == builder.role;
        }

        @Override
        public int hashCode() {
            return Objects.hash(personId, name, surname, password, phoneNumber, role);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "personId=" + personId +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", password='" + password + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", role=" + role +
                    '}';
        }
    }
}
