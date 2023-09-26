package com.Spendify.Spendify.User;

import org.springframework.beans.BeanUtils;
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
        System.out.println("test");
        User user = userRepository.getReferenceById(userId);
        System.out.println("user");
        try {
            // Skopiuj niepuste właściwości z obiektu UserUpdateRequest do obiektu User
            BeanUtils.copyProperties(user, updateRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error copying properties", e);
        }
        // Zapisz zaktualizowanego użytkownika do bazy danych
        userRepository.save(user);
    }
}
