package com.contact.contactmanager.Service;

import com.contact.contactmanager.Repository.AppUserRepository;
import com.contact.contactmanager.model.AppUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppUserDetailsServiceTest {

    private final AppUserRepository userRepository = mock(AppUserRepository.class);
    private final AppUserDetailsService service = new AppUserDetailsService(userRepository);

    @Test
    void loadUserByUsername_found() {
        AppUser user = new AppUser("testuser", "password", "test@example.com", "1234567890", "ROLE_USER,ROLE_ADMIN");
        when(userRepository.findById("testuser")).thenReturn(Optional.of(user));

        UserDetails userDetails = service.loadUserByUsername("testuser");
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void loadUserByUsername_notFound() {
        when(userRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("missing");
        });
    }
}
