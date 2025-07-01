package com.datamanager.datamanipulation.Service;

import com.datamanager.datamanipulation.Model.UserData;
import com.datamanager.datamanipulation.Repository.ContactDataRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
//
public class ContactDataService {


    @Autowired
    private final ContactDataRepository contactDataRepository;

    public ContactDataService(ContactDataRepository contactDataRepository) {
        this.contactDataRepository = contactDataRepository;
    }

    public List<UserData> getAllContactDetails(){
       return contactDataRepository.findAll();
   }
    public UserData getDataById(Integer fileNumber) {
        return contactDataRepository.findByFileNumber(fileNumber);
    }

   public UserData createData(UserData userData){
        return contactDataRepository.save(userData);
   }

}
