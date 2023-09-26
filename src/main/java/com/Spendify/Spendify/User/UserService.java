package com.Spendify.Spendify.User;

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
                .orElseThrow(() -> new IllegalStateException("User not found with ID:" + userId));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(Long userId, UserUpdateRequest updateRequest) {
        User user = userRepository.getReferenceById(userId);

        if (updateRequest.surname() != null) user
                .setSurname(updateRequest.surname());

        if (updateRequest.email() != null) user
                .setEmail(updateRequest.email());

        if (updateRequest.password() != null) user
                .setEmail(updateRequest.password());

        if (updateRequest.image() != null) user
                .setEmail(updateRequest.image());

        userRepository.save(user);
    }
}
