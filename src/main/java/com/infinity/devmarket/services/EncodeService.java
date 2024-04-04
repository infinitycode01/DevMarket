package com.infinity.devmarket.services;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncodeService {
    public String encode(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    public String decode(String s) {
        byte[] decodedBytes = Base64.getDecoder().decode(s);
        return new String(decodedBytes);
    }
}
