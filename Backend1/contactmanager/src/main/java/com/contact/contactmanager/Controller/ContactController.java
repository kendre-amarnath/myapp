package com.contact.contactmanager.Controller;
//package com.contact.contactmanager.controller;

import com.contact.contactmanager.Exception.ResourceNotFoundException;
import com.contact.contactmanager.Repository.UserDataRepository;
import com.contact.contactmanager.model.UserData;
import com.contact.contactmanager.Service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userdata")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserDataRepository userDataRepository;

    @GetMapping
    public List<UserData> getAllUsers() {
        return userDataService.getAllUsers();
    }

    @GetMapping("/fileNumber/{fileNumber}")
    public ResponseEntity<UserData> getUserByFileNumber(@PathVariable String fileNumber) {
        return userDataService.getUserByFileNumber(fileNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/fileNumber/{fileNumber}")
    public ResponseEntity<UserData> updateUserByFileNumber(@PathVariable String fileNumber,@RequestBody UserData userData){

        UserData updateUser=userDataRepository.findByFileNumber(fileNumber).orElseThrow(()->new ResourceNotFoundException("contact with fileNumber :"+fileNumber));


        updateUser.setFirstName(userData.getFirstName());
        updateUser.setLastName(userData.getLastName());
        updateUser.setGender(userData.getGender());
        updateUser.setDateOfBirth(userData.getDateOfBirth());
        updateUser.setAddress1(userData.getAddress1());
        updateUser.setAddress2(userData.getAddress2());
        updateUser.setPhoneNumber1(userData.getPhoneNumber1());
        updateUser.setPhoneNumber2(userData.getPhoneNumber2());
        UserData savedUser = userDataRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @PostMapping
    public UserData saveUser(@RequestBody UserData userData) {
        return userDataService.saveUser(userData);
    }
    @DeleteMapping("/fileNumber/{fileNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String fileNumber) {
        userDataService.deleteUserByFileNumber(fileNumber);
        return ResponseEntity.noContent().build();
    }
}
