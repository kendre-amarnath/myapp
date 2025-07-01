package com.datamanager.datamanipulation.Controller;

import com.datamanager.datamanipulation.Model.UserData;
import com.datamanager.datamanipulation.Service.ContactDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contact")
@RestController
public class ContactDataController {
    @Autowired
    private ContactDataService contactDataService;

    @GetMapping
    public ResponseEntity<List<UserData>> getAllContactDetails() {
        List<UserData> contactData1 = contactDataService.getAllContactDetails();
        return ResponseEntity.ok(contactData1);
    }

    @GetMapping("/{fileNumber}")
    public ResponseEntity<UserData> getDataById(@PathVariable Integer fileNumber) {
        UserData userdata = contactDataService.getDataById(fileNumber);
        return ResponseEntity.ok(userdata);
    }

    @PostMapping("/add")
    public ResponseEntity<UserData> createData(@RequestBody UserData userData) {
        UserData savedData = contactDataService.createData(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }



}
