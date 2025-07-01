package com.contact.contactmanager.Repository;

import com.contact.contactmanager.model.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class UserDataRepositoryTest {

    @Autowired
    private UserDataRepository userDataRepository;



    @Test
    void deleteByFileNumber() {
        UserData userData = new UserData();
        userData.setFileNumber("DEL123");
        userData.setFirstName("Jane Doe");
        userDataRepository.save(userData);

        userDataRepository.deleteByFileNumber("DEL123");

        Optional<UserData> found = userDataRepository.findByFileNumber("DEL123");
        assertTrue(found.isEmpty());
    }

    @Test
    void findByFileNumber_notFound() {
        Optional<UserData> found = userDataRepository.findByFileNumber("NOTEXIST");
        assertTrue(found.isEmpty());
    }
}
