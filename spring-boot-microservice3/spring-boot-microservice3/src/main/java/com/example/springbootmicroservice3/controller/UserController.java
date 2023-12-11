package com.example.springbootmicroservice3.controller;

import com.example.springbootmicroservice3.model.Role;
import com.example.springbootmicroservice3.model.Statut;
import com.example.springbootmicroservice3.model.User;
import com.example.springbootmicroservice3.service.UserService;
import com.example.springbootmicroservice3.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user") //pre-path
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storage;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/byRole/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable Role role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
   @PutMapping("change/{role}")//api/user/change/{role}
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal User user, @PathVariable Role role)
    {
        userService.changeRole(role, user.getUsername());

        return ResponseEntity.ok(true);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(
            @RequestParam(value = "file", required = false) MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("phone") String phone,
            @RequestParam("adresse") String adresse,
            @RequestParam("status") Statut status,
            @PathVariable Long userId) {

        User updatedUser = new User();
        updatedUser.setName(name);
        updatedUser.setUsername(username);
        updatedUser.setPhone(phone);
        updatedUser.setAdresse(adresse);
        updatedUser.setStatut(status);

        User updatedUserResponse = userService.updateUser(userId, image, updatedUser);

        if (updatedUserResponse != null) {
            return ResponseEntity.ok(updatedUserResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<org.springframework.core.io.Resource> getFile(@PathVariable String filename) {
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }

}
