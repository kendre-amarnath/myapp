package com.contact.contactmanager.Repository;

import com.contact.contactmanager.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDataRepository extends MongoRepository<UserData, String> {

    Optional<UserData> findByFileNumber(String fileNumber);

    void deleteByFileNumber(String fileNumber);
}
