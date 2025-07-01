package com.contact.contactmanager.Dtos;
public record AuthRequest(
        String username,
        String password,
        String email,
        String phoneNumber
) {}