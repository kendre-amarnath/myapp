package com.contact.contactmanager.Controller;

import com.contact.contactmanager.Service.UserDataService;
import com.contact.contactmanager.model.UserData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockitoBean
    private UserDataService userDataService;

    @Test
    void getAllUsers() throws Exception {
        when(userDataService.getAllUsers()).thenReturn(List.of(new UserData()));
        mockMvc.perform(get("/api/userdata"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByFileNumber_found() throws Exception {
        when(userDataService.getUserByFileNumber("123")).thenReturn(Optional.of(new UserData()));
        mockMvc.perform(get("/api/userdata/fileNumber/123"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByFileNumber_notFound() throws Exception {
        when(userDataService.getUserByFileNumber("notfound")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/userdata/fileNumber/notfound"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveUser() throws Exception {
        UserData user = new UserData();
        when(userDataService.saveUser(any(UserData.class))).thenReturn(user);
        mockMvc.perform(post("/api/userdata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        doNothing().when(userDataService).deleteUserByFileNumber("123");
        mockMvc.perform(delete("/api/userdata/fileNumber/123"))
                .andExpect(status().isNoContent());
    }
}
