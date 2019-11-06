package my.project.model.domain;

import my.project.model.entity.Role;

public class User {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }

    public User(User user, String password) {
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.password = password;
        this.role = user.role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Integer id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private Role role;

        private UserBuilder() {}

        public UserBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
