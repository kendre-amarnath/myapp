package com.datamanager.datamanipulation.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.datamanager.datamanipulation.Model.UserData;

import java.util.Optional;

public interface ContactDataRepository extends MongoRepository<UserData, ObjectId> {
    UserData findByFileNumber(Integer fileNumber);
    // custom query methods if needed
}