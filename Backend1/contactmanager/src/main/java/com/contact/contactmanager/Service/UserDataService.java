package com.contact.contactmanager.Service;



import com.contact.contactmanager.model.UserData;
import com.contact.contactmanager.Repository.UserDataRepository;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    public List<UserData> getAllUsers() {
        return userDataRepository.findAll();
    }

    public Optional<UserData> getUserByFileNumber(String fileNumber) {
        return userDataRepository.findByFileNumber(fileNumber);
    }

    public UserData saveUser(UserData userData) {
        return userDataRepository.save(userData);
    }

    public void deleteUserByFileNumber(String fileNumber) {
        userDataRepository.deleteByFileNumber(fileNumber);
    }


}
