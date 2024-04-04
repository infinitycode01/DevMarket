package com.infinity.devmarket.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminService {
    public void doAdminStuff() {
        System.out.println("Only admin here");
    }
}
