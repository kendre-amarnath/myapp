package com.contact.contactmanager.Repository;

import com.contact.contactmanager.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void saveAndFindByUsername() {
        AppUser user = new AppUser("testuser", "password", "test@example.com", "1234567890", "ROLE_USER");
        appUserRepository.save(user);

        Optional<AppUser> found = appUserRepository.findByUsername("testuser");
        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }

    @Test
    void findByUsername_notFound() {
        Optional<AppUser> found = appUserRepository.findByUsername("nonexistent");
        assertTrue(found.isEmpty());
    }
}
