package com.Spendify.Spendify.User;

import com.Spendify.Spendify.exception.DuplicateResourceException;
import com.Spendify.Spendify.exception.FieldRequiredException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());

    }

    public UserDTO getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with ID [%s] not found".formatted(userId)
                ));
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User with ID [%s] not found".formatted(userId)
        ));
        userRepository.deleteById(userId);
    }

    public void updateUser(Long userId, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User with ID [%s] not found".formatted(userId)
        ));

        if (updateRequest.surname() != null) user
                .setSurname(updateRequest.surname());

        if (updateRequest.email() != null) user
                .setEmail(updateRequest.email());

        if (updateRequest.password() != null) user
                .setPassword(updateRequest.password());

        if (updateRequest.image() != null) user
                .setImage(updateRequest.image());

        userRepository.save(user);
    }

    public UserDTO addUser(UserAddRequestDTO addRequest) {
        if (existsUserWithEmail(addRequest.email())) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }
        User user = new User(
                existsResource("name", addRequest.name()),
                existsResource("surname", addRequest.surname()),
                existsResource("password", addRequest.password()),
                existsResource("email", addRequest.email()),
                existsResource("image", addRequest.image()),
                addRequest.isActive()
        );
        userRepository.save(user);
        return userRepository.findById(user.getId())
                .map(userDTOMapper)
                .orElseThrow(()->new ResourceNotFoundException("User with ID [%s] is not found"
                .formatted(user.getId())));
    }

    public boolean existsUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public String existsResource(String elementName, String element) {
        if (element == null) throw new FieldRequiredException("please fill " + elementName + "field");
        return element;
    }
}
