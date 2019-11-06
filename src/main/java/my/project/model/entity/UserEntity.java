package my.project.model.entity;

public class UserEntity {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;

    private UserEntity(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;

    }

    public UserEntity(UserEntity userEntity, String password) {
        this.id = userEntity.id;
        this.name = userEntity.name;
        this.surname = userEntity.surname;
        this.email = userEntity.email;
        this.password = password;
        this.role = userEntity.role;
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

        private UserBuilder() {
        }

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


        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
