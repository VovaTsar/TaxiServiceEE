package my.project.model.service.impl;

import my.project.model.domain.User;
import my.project.model.exception.AlreadyRegisteredException;
import my.project.model.exception.InvalidEncodingException;
import my.project.model.exception.UserNotFoundException;
import my.project.model.service.validator.Validator;
import org.apache.log4j.Logger;
import my.project.model.dao.UserDao;
import my.project.model.entity.UserEntity;
import my.project.model.service.encoder.PasswordEncoder;
import my.project.model.service.mapper.UserMapper;
import my.project.model.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;
    private final Validator<User> validator;

    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder, UserMapper mapper, Validator<User> validator) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean register(User user) {
        validator.validate(user);

        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("UserEntity is already registered by this e-mail");
            throw new AlreadyRegisteredException("UserEntity is already registered by this e-mail");
        }

        String encoded = encoder.encode(user.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        User userWithEncodedPass = new User(user, encoded);

        return userDao.save(mapper.mapUserToUserEntity(userWithEncodedPass));
    }

    @Override
    public User login(String email, String password) {
        String encodedPassword = encoder.encode(password).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        Optional<UserEntity> entity = userDao.findByEmail(email);

        if (!entity.isPresent()) {
            LOGGER.warn("There is no servlet with this e-mail");
            throw new UserNotFoundException("There is no servlet with this e-mail");
        } else {
            if (entity.get().getPassword().equals(encodedPassword)) {
                return mapper.mapUserEntityToUser(entity.get());
            } else {
                LOGGER.warn("Incorrect password");
                throw new UserNotFoundException("Incorrect password");
            }
        }
    }


    @Override
    public List<User> findAll() {
        List<UserEntity> result = userDao.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }


}