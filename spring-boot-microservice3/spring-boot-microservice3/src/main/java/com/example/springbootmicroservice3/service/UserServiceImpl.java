package com.example.springbootmicroservice3.service;

import com.example.springbootmicroservice3.model.Role;
import com.example.springbootmicroservice3.model.User;
import com.example.springbootmicroservice3.repository.UserRepository;
import com.example.springbootmicroservice3.utils.StorageService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private StorageService storageService;
    /*@Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        //user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }*/

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    @Transactional //Transactional is required when executing an update/delete query.
    public void changeRole(Role newRole, String username)
    {
        userRepository.updateUserRole(username, newRole);
    }
    @Transactional
    public User updateUser(Long userId, MultipartFile image, User userUpdateRequest) {
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null && existingUser.getId().equals(userId) && userUpdateRequest != null) {
            existingUser.setName(userUpdateRequest.getName());
            existingUser.setUsername(userUpdateRequest.getUsername());
            existingUser.setPhone(userUpdateRequest.getPhone());
            existingUser.setAdresse(userUpdateRequest.getAdresse());
            existingUser.setStatut(userUpdateRequest.getStatut());

            if (image != null && !image.isEmpty()) {
                String filename = StringUtils.cleanPath(image.getOriginalFilename());

                existingUser.setImage(filename);
                storageService.store(image);
            }

            User updatedUser = userRepository.save(existingUser);
            return updatedUser;
        }

        return null;
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
