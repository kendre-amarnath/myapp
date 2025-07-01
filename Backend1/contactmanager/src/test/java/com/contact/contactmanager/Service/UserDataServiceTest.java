package com.contact.contactmanager.Service;

import com.contact.contactmanager.Repository.UserDataRepository;
import com.contact.contactmanager.model.UserData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDataServiceTest {

    private final UserDataRepository userDataRepository = mock(UserDataRepository.class);
    private final UserDataService service = new UserDataService();

    public UserDataServiceTest() {
        // Inject mocked repository into service via reflection or setter
        // Since field is @Autowired private, we'll use reflection here:
        try {
            var field = UserDataService.class.getDeclaredField("userDataRepository");
            field.setAccessible(true);
            field.set(service, userDataRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllUsers() {
        List<UserData> users = List.of(new UserData(), new UserData());
        when(userDataRepository.findAll()).thenReturn(users);

        List<UserData> result = service.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void getUserByFileNumber_found() {
        UserData user = new UserData();
        when(userDataRepository.findByFileNumber("file1")).thenReturn(Optional.of(user));

        Optional<UserData> result = service.getUserByFileNumber("file1");
        assertTrue(result.isPresent());
    }

    @Test
    void getUserByFileNumber_notFound() {
        when(userDataRepository.findByFileNumber("file2")).thenReturn(Optional.empty());

        Optional<UserData> result = service.getUserByFileNumber("file2");
        assertTrue(result.isEmpty());
    }

    @Test
    void saveUser() {
        UserData user = new UserData();
        when(userDataRepository.save(user)).thenReturn(user);

        UserData saved = service.saveUser(user);
        assertNotNull(saved);
        verify(userDataRepository).save(user);
    }

    @Test
    void deleteUserByFileNumber() {
        doNothing().when(userDataRepository).deleteByFileNumber("file3");

        service.deleteUserByFileNumber("file3");
        verify(userDataRepository).deleteByFileNumber("file3");
    }
}
