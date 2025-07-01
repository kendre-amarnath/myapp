package com.datamanager.datamanipulation.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Document("UserData")
public class UserData {
    @Id
    private ObjectId id;

    private Integer fileNumber;

    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String address1;
    private String address2;
    private String phoneNumber1;
    private String phoneNumber2;
    //file structure
    //fileNUmber,firstName,lastName,Gender,DOB,Address1,Address2,phone1,phone2


}
