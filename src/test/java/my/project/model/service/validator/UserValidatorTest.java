package my.project.model.service.validator;

import my.project.model.domain.User;
import my.project.model.exception.RegistrationRuntimeException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserValidatorTest {
    private static UserValidator validator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void initUser() {
        validator = new UserValidator();
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionValidatingNullUser() {
        exception.expect(RegistrationRuntimeException.class);
        exception.expectMessage("UserEntity is not valid");

        validator.validate(null);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionValidatingInvalidName() {
        User student = User.builder().withName("n").build();
        exception.expect(RegistrationRuntimeException.class);
        exception.expectMessage("Incorrect name");

        validator.validate(student);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionValidatingInvalidSurname() {
        User student = User.builder().withName("Name").
                withSurname("s").
                build();
        exception.expect(RegistrationRuntimeException.class);
        exception.expectMessage("Incorrect surname");

        validator.validate(student);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionValidatingInvalidEmail() {
        User student = User.builder().withName("Name").
                withSurname("Surname").
                withEmail("uncorrect").
                build();
        exception.expect(RegistrationRuntimeException.class);
        exception.expectMessage("Incorrect e-mail");

        validator.validate(student);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionValidatingInvalidPassword() {
        User student = User.builder().withName("Name").
                withSurname("Surname").
                withEmail("correct@gmail.com").
                withPassword("bad").
                build();
        exception.expect(RegistrationRuntimeException.class);
        exception.expectMessage("Incorrect password");

        validator.validate(student);
    }
}